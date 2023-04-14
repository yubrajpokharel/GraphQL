package com.yubraj.graphql.service;

import com.yubraj.graphql.dao.CitiesDao;
import com.yubraj.graphql.domain.Cities;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class GraphQLService {

  @Getter private GraphQL graphQL;

  CitiesDao citiesDao;

  public GraphQLService(CitiesDao citiesDao) {
    this.citiesDao = citiesDao;
  }

  private File getSchemaFile() {
    Resource resource = new ClassPathResource("graphql/schema.graphqls");
    try {
      return resource.getFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @PostConstruct
  public void loadSchema() throws IOException {
    File schemaFile = getSchemaFile();
    TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
    RuntimeWiring wiring = buildWiring();
    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
    graphQL = GraphQL.newGraphQL(schema).build();
  }

  private RuntimeWiring buildWiring() {
    DataFetcher<List<Cities>> cities = data -> (List<Cities>) citiesDao.findAll();
    DataFetcher<List<Cities>> cityByCountry =
        data -> citiesDao.findAllByCountry(data.getArgument("country"));
    DataFetcher<Optional<Cities>> cityById = data -> citiesDao.findById(data.getArgument("id"));

    return RuntimeWiring.newRuntimeWiring()
        .type(
            "Query",
            typeWriting ->
                typeWriting
                    .dataFetcher("cities", cities)
                    .dataFetcher("cityByCountry", cityByCountry)
                    .dataFetcher("cityById", cityById))
        .build();
  }
}
