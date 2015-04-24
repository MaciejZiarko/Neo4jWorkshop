package ziarko.neo4j.domain

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import ziarko.neo4j.domain.model.RssFeed

interface RssFeedRepository extends GraphRepository<RssFeed> {

    @Query('''MATCH (feed:RssFeed)<-[:PUBLISHED_IN]-(a:Article)-[:CONTAINS]->(ne:NamedEntity)
              WHERE ne.name = {0}
              RETURN count(DISTINCT feed)''')
    int countRssFeedsThatMentionedNamedEntity(String namedEntity)

}
