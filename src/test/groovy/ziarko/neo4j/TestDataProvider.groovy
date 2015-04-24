package ziarko.neo4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ziarko.neo4j.domain.ArticleRepository
import ziarko.neo4j.domain.NamedEntityRepository
import ziarko.neo4j.domain.RssFeedRepository
import ziarko.neo4j.domain.model.Article
import ziarko.neo4j.domain.model.NamedEntity
import ziarko.neo4j.domain.model.RssFeed

import javax.annotation.PostConstruct
import java.time.LocalDate

@Component
class TestDataProvider {

    private final RssFeedRepository rssFeedRepository
    private final ArticleRepository articleRepository
    private final NamedEntityRepository namedEntityRepository

    @Autowired
    TestDataProvider(RssFeedRepository rssFeedRepository,
                     ArticleRepository articleRepository,
                     NamedEntityRepository namedEntityRepository) {
        this.rssFeedRepository = rssFeedRepository
        this.articleRepository = articleRepository
        this.namedEntityRepository = namedEntityRepository
    }

    @PostConstruct
    public init() {
        Map<String, RssFeed> feeds = getTestRssFeeds()
        feeds.values().each { rssFeedRepository.save(it) }

        Map<String, NamedEntity> namedEntities = getTestNamedEntities()
        namedEntities.values().each { namedEntityRepository.save(it) }

        List<Article> articles = getTestArticles(feeds, namedEntities)
        articles.each { articleRepository.save(it) }
    }

    private static Map<String, RssFeed> getTestRssFeeds() {
        return [
                'AJE': new RssFeed('Al Jazeera English'),
                'BBC': new RssFeed('BBC World'),
                'WP' : new RssFeed('Washington Post')
        ]
    }

    private static Map<String, NamedEntity> getTestNamedEntities() {
        return [
                'China'              : new NamedEntity('China'),
                'LegislativeCouncil' : new NamedEntity('Legislative Council'),
                'HK'                 : new NamedEntity('Hong Kong'),
                'OccupyCentral'      : new NamedEntity('Occupy Central'),
                'LiTingting'         : new NamedEntity('Li Tingting'),
                'MercedesBenz'       : new NamedEntity('Mercedes-Benz'),
                'Volkswagen'         : new NamedEntity('Volkswagen'),
                'Germany'            : new NamedEntity('Germany'),
                'MitsubishiElectric' : new NamedEntity('Mitsubishi Electric'),
                'Japan'              : new NamedEntity('Japan'),
                'Maglev'             : new NamedEntity('Maglev'),
                'CentralJapanRailway': new NamedEntity('Central Japan Railway'),
                'Tokio'              : new NamedEntity('Tokio'),
                'ShinzoAbe'          : new NamedEntity('Shinzo Abe'),
                'Indonesia'          : new NamedEntity('Indonesia'),
                'Pakistan'           : new NamedEntity('Pakistan'),
                'alQaeda'            : new NamedEntity('al-Qaeda'),
                'USA'                : new NamedEntity('USA'),
                'Italy'              : new NamedEntity('Italy'),
                'XiJinping'          : new NamedEntity('Xi Jinping'),
                'LiChuncheng'        : new NamedEntity('Li Chuncheng'),
                'Sichuan'            : new NamedEntity('Sichuan'),
        ]
    }

    private static List<Article> getTestArticles(Map<String, RssFeed> feeds, Map<String, NamedEntity> namedEntities) {
        return [
                new Article(
                        'http://www.aljazeera.com/news/2015/04/hong-kong-electoral-reform-150422042614835.html',
                        'Hong Kong election candidates to be screened',
                        feeds.AJE,
                        LocalDate.of(2015, 4, 22),
                        [namedEntities.China, namedEntities.HK, namedEntities.OccupyCentral, namedEntities.LegislativeCouncil]
                ),
                new Article(
                        'http://www.aljazeera.com/news/2015/04/china-women-rights-activists-150423044736369.html',
                        'Freed Chinese feminist refuses to be silenced',
                        feeds.AJE,
                        LocalDate.of(2015, 4, 23),
                        [namedEntities.China, namedEntities.LiTingting]
                ),
                new Article(
                        'http://www.bbc.com/news/business-32426042',
                        'China fines Daimler\'s Mercedes-Benz for price-fixing',
                        feeds.BBC,
                        LocalDate.of(2015, 4, 23),
                        [namedEntities.China, namedEntities.MercedesBenz, namedEntities.Volkswagen, namedEntities.Germany,
                         namedEntities.MitsubishiElectric, namedEntities.Japan]
                ),
                new Article(
                        'http://www.aljazeera.com/news/2015/04/japan-breaks-train-speed-record-maglev-train-150421085448739.html',
                        'Japan breaks train speed record with Maglev train',
                        feeds.AJE,
                        LocalDate.of(2015, 4, 21),
                        [namedEntities.Japan, namedEntities.Tokio, namedEntities.ShinzoAbe,
                         namedEntities.CentralJapanRailway, namedEntities.Germany, namedEntities.Maglev]
                ),
                new Article(
                        'http://www.aljazeera.com/news/2015/04/drone-laced-radiation-lands-japan-pm-office-150422185726666.html',
                        'Drone laced with radiation lands on Japan PM\'s office',
                        feeds.AJE,
                        LocalDate.of(2015, 4, 22),
                        [namedEntities.Japan, namedEntities.Tokio, namedEntities.ShinzoAbe, namedEntities.Indonesia, namedEntities.USA]
                ),
                new Article(
                        'http://www.bbc.com/news/world-asia-32410001',
                        'Japan-China: PM Abe offers \'remorse\' but no WW2 apology',
                        feeds.BBC,
                        LocalDate.of(2015, 4, 22),
                        [namedEntities.Japan, namedEntities.China, namedEntities.ShinzoAbe, namedEntities.XiJinping]
                ),
                new Article(
                        'http://www.bbc.com/news/world-asia-china-32426024',
                        'China official Li Chuncheng on trial for corruption',
                        feeds.BBC,
                        LocalDate.of(2015, 4, 23),
                        [namedEntities.China, namedEntities.LiChuncheng, namedEntities.Sichuan]
                ),
                new Article(
                        'http://www.bbc.com/news/world-asia-china-32397179',
                        'Hong Kong election candidates to be screened, reform proposal says',
                        feeds.BBC,
                        LocalDate.of(2015, 4, 22),
                        [namedEntities.LegislativeCouncil, namedEntities.China, namedEntities.HK]
                ),
                new Article(
                        'http://www.washingtonpost.com/world/national-security/us-operation-kills-al-qaeda-hostages-including-american/2015/04/23/8e9fcaba-e9bd-11e4-aae1-d642717d8afa_story.html?hpid=z1',
                        'U.S. drone strike killed al-Qaeda hostages, including American',
                        feeds.WP,
                        LocalDate.of(2015, 4, 23),
                        [namedEntities.Pakistan, namedEntities.alQaeda, namedEntities.USA, namedEntities.Italy]
                )
        ]
    }
}
