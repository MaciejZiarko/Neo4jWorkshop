package ziarko.neo4j.domain

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import ziarko.neo4j.domain.model.RssFeed

interface RssFeedRepository extends GraphRepository<RssFeed> {

    @Query('''''')
    int countRssFeedsThatMentionedNamedEntity(String namedEntity)

}
