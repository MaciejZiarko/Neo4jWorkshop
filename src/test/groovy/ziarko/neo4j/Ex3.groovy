package ziarko.neo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import ziarko.neo4j.domain.NamedEntityRepository
import ziarko.neo4j.domain.model.NamedEntityRankingPosition

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class Ex3 extends Specification {

    @Autowired
    NamedEntityRepository namedEntityRepository

    def '''Method getMostPopularNamedEntitiesForDay should return list of the most popular named entities
            on given day ordered by number of articles that mentioned it. When two named entities have the same number
            of articles, entities should be ordered by name'''() {
        when:
            List<NamedEntityRankingPosition> position = namedEntityRepository.getMostPopularNamedEntitiesForDay('2015-04-22')
        then:
            position.size() == 10
        and:
            position[0].namedEntity.name == 'China'
            position[0].count == 3
        and:
            position[1].namedEntity.name == 'Hong Kong'
            position[1].count == 2
        and:
            position[2].namedEntity.name == 'Japan'
            position[2].count == 2
        and:
            position[7].namedEntity.name == 'Tokio'
            position[7].count == 1
    }

}
