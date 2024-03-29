package bp.demo.iot.operation.manager.repository.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@Configuration
@PropertySource(value = {"classpath:iot-operation-manager.properties"})
@EnableCassandraRepositories(
        basePackages = {"bp.demo.iot.operation.manager.repository.dao"},
        cassandraTemplateRef = "IotOperationSpaceCassandraTemplate")
public class CassandraConfig {

  private static final Logger logger =
          LoggerFactory.getLogger(CassandraConfig.class);

  private static final String CASSANDRA_HOST_PROPERTY =
          "bp.demo.iot.operation.cassandra.host";
  private static final String CASSANDRA_PORT_PROPERTY =
          "bp.demo.iot.operation.cassandra.port";
  private static final String CASSANDRA_KEYSPACE_PROPERTY =
          "bp.demo.iot.operation.cassandra.keyspace";

  @Autowired
  private Environment environment;

  @Bean
  public CassandraClusterFactoryBean cluster() {
    CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();

    String cassandraHost = environment.getProperty(CASSANDRA_HOST_PROPERTY);
    if (System.getProperty(CASSANDRA_HOST_PROPERTY) != null) {
      cassandraHost = System.getProperty(CASSANDRA_HOST_PROPERTY);
    }

    String cassandraPort = environment.getProperty(CASSANDRA_PORT_PROPERTY);
    if (System.getProperty(CASSANDRA_PORT_PROPERTY) != null) {
      cassandraPort = System.getProperty(CASSANDRA_PORT_PROPERTY);
    }

    logger.info("Using Cassandra host=" + cassandraHost + " port=" + cassandraPort);

    cluster.setContactPoints(cassandraHost);
    cluster.setPort(Integer.parseInt(cassandraPort));
    cluster.setMetricsEnabled(false);
    return cluster;
  }

  @Bean
  public CassandraMappingContext cassandraMapping() {
    return new CassandraMappingContext();
  }

  @Bean
  protected String getKeyspaceName() {
    return environment.getProperty(CASSANDRA_KEYSPACE_PROPERTY);
  }

  @Bean
  public CassandraMappingContext mappingContext() {

    CassandraMappingContext mappingContext = new CassandraMappingContext();
    mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), getKeyspaceName()));

    return mappingContext;
  }

  @Bean
  public CassandraConverter converter() {
    return new MappingCassandraConverter(mappingContext());
  }

  @Bean("IotOperationSpaceSession")
  public CassandraSessionFactoryBean session()
          throws Exception {

    CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
    session.setCluster(cluster().getObject());
    session.setKeyspaceName(getKeyspaceName());
    session.setConverter(converter());
    session.setSchemaAction(SchemaAction.NONE);

    return session;
  }

  @Bean("IotOperationSpaceCassandraTemplate")
  public CassandraOperations cassandraTemplate()
          throws Exception {
    return new CassandraTemplate(session().getObject());
  }

}
