package ziarko.neo4j.domain.model

import com.google.common.collect.ImmutableList
import groovy.transform.ToString
import org.neo4j.graphdb.Direction
import org.springframework.data.neo4j.annotation.GraphId
import org.springframework.data.neo4j.annotation.Indexed
import org.springframework.data.neo4j.annotation.NodeEntity
import org.springframework.data.neo4j.annotation.RelatedTo

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@NodeEntity
@ToString(includeFields = true, includePackage = false)
class Article {

    @GraphId
    private Long graphId

    @Indexed(unique = true)
    private String url

    private String title

    @Indexed
    private String publishDate

    @RelatedTo(type = 'PUBLISHED_IN', direction = Direction.OUTGOING)
    private RssFeed publishedIn

    @RelatedTo(type = 'CONTAINS', direction = Direction.OUTGOING)
    private Set<NamedEntity> containedNamedEntities

    public Article(String url, String title, RssFeed publishedIn,
                   LocalDate publishDate, List<NamedEntity> containedNamedEntities) {
        this.url = url
        this.title = title
        this.publishedIn = publishedIn
        this.publishDate = DateTimeFormatter.ISO_DATE.format(publishDate)
        this.containedNamedEntities = containedNamedEntities
    }

    private Article() {

    }

    String getUrl() {
        return url
    }

    String getTitle() {
        return title
    }

    LocalDate getPublishDate() {
        return LocalDate.parse(publishDate, DateTimeFormatter.ISO_DATE)
    }

    RssFeed getPublishedIn() {
        return publishedIn
    }

    List<NamedEntity> getContainedNamedEntities() {
        return ImmutableList.copyOf(containedNamedEntities)
    }
}
