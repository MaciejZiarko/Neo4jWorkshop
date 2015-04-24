package ziarko.neo4j.domain

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import ziarko.neo4j.domain.model.Article

interface ArticleRepository extends GraphRepository<Article> {

    @Query('')
    List<Article> getArticlesPublishedOnDay(String isoDate)

    @Query('''''')
    List<Article> getArticlesThatMentionedFirstButNotMentionSecondNamedEntity(String firstNamedEntity, String secondNamedEntity)
}
