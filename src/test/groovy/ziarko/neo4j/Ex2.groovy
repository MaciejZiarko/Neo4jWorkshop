package ziarko.neo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import ziarko.neo4j.domain.RssFeedRepository

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class Ex2 extends Specification {

    @Autowired
    RssFeedRepository rssFeedRepository

    def "Method countRssFeedsThatMentionedNamedEntity should return number of feeds that mentioned given named entity"() {
        expect:
            rssFeedRepository.countRssFeedsThatMentionedNamedEntity(namedEntity) == expectedResult
        where:
            namedEntity | expectedResult
            'China'     | 2
            'Italy'     | 1
            'Japan'     | 2
            'USA'       | 2
            'Poland'    | 0
    }

}
