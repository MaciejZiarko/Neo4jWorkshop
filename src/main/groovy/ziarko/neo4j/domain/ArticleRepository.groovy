package ziarko.neo4j.domain

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import ziarko.neo4j.domain.model.Article

interface ArticleRepository extends GraphRepository<Article> {

    @Query('MATCH (a:Article) WHERE a.publishDate = {0} RETURN a')
    List<Article> getArticlesPublishedOnDay(String isoDate)

    @Query('''MATCH (a:Article)-[:CONTAINS]->(ne:NamedEntity)
              WHERE ne.name = {0} AND (NOT (a:Article)-[:CONTAINS]->(:NamedEntity {name : {1}}))
              RETURN a''')
    List<Article> getArticlesThatMentionedFirstButNotMentionSecondNamedEntity(String firstNamedEntity, String secondNamedEntity)
}
