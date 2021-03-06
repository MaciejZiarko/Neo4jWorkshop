package ziarko.neo4j.domain.model

import groovy.transform.ToString
import org.springframework.data.neo4j.annotation.GraphId
import org.springframework.data.neo4j.annotation.Indexed
import org.springframework.data.neo4j.annotation.NodeEntity

@NodeEntity
@ToString(includeFields = true, includePackage = false)
class RssFeed {

    @GraphId
    private Long graphId

    @Indexed(unique = true)
    private String name

    RssFeed(String name) {
        this.name = name
    }

    String getName() {
        return name
    }

    private RssFeed() {

    }
}
