package ziarko.neo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import ziarko.neo4j.domain.ArticleRepository

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
class Ex4 extends Specification {

    @Autowired
    ArticleRepository articleRepository

    def '''Method getArticlesThatMentionedFirstButNotMentionSecondNamedEntity
           should return articles that mentioned first given named entity but did not mention second'''() {
        expect:
            articleRepository.getArticlesThatMentionedFirstButNotMentionSecondNamedEntity(first, second).size() == expectedSize
        where:
            first       | second      | expectedSize
            'China'     | 'Hong Kong' | 4
            'Hong Kong' | 'China'     | 0
            'Japan'     | 'Poland'    | 4
    }

}
