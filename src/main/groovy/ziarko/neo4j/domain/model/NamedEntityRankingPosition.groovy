package ziarko.neo4j.domain.model

import groovy.transform.ToString
import org.springframework.data.neo4j.annotation.QueryResult
import org.springframework.data.neo4j.annotation.ResultColumn

@QueryResult
@ToString(includeFields = true, includePackage = false)
public class NamedEntityRankingPosition {

    @ResultColumn('namedEntity')
    NamedEntity namedEntity

    @ResultColumn('count')
    long count

}
