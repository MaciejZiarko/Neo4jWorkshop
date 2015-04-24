package ziarko.neo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import ziarko.neo4j.domain.ArticleRepository

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class Ex1 extends Specification {

    @Autowired
    ArticleRepository articleRepository

    def "Method getArticlesPublishedOnDay should return articles that were published on particular date"() {
        expect:
            articleRepository.getArticlesPublishedOnDay(isoPublishDate).size() == expectedResultSize
        where:
            isoPublishDate | expectedResultSize
            '2015-04-23'   | 4
            '2015-04-22'   | 4
            '2015-04-21'   | 1
            '2015-04-20'   | 0
    }

}
