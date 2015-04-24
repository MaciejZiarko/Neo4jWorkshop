package ziarko.neo4j.domain

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import ziarko.neo4j.domain.model.NamedEntity
import ziarko.neo4j.domain.model.NamedEntityRankingPosition

interface NamedEntityRepository extends GraphRepository<NamedEntity> {

    @Query(value = '''''')
    List<NamedEntityRankingPosition> getMostPopularNamedEntitiesForDay(String isoPublishDay)


}
