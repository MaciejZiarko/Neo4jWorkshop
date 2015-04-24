package ziarko.neo4j.infrastructure

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.kernel.impl.util.FileUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.config.EnableNeo4jRepositories
import org.springframework.data.neo4j.config.Neo4jConfiguration

@Configuration
@EnableNeo4jRepositories(basePackages = 'ziarko.neo4j')
class InfraConfig extends Neo4jConfiguration {

    public static final String BASE_PACKAGE = 'ziarko.neo4j'
    public static final String DB_DIR = 'neo4j.db'

    InfraConfig() {
        setBasePackage(BASE_PACKAGE)
    }

    @Bean
    GraphDatabaseService graphDatabaseService() {
        FileUtils.deleteRecursively(new File(DB_DIR))
        return new GraphDatabaseFactory().newEmbeddedDatabase(DB_DIR)
    }

}
