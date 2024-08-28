package learningtest.com.google.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CacheBuilder}.
 *
 * @author Johnny Lim
 */
class CacheBuilderTests {

    @Test
    void statsWithoutRecordStats() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();

        cache.put("a", "1");
        assertThat(cache.getIfPresent("a")).isEqualTo("1");
        assertThat(cache.getIfPresent("b")).isNull();

        CacheStats stats = cache.stats();
        assertThat(stats.hitCount()).isEqualTo(0L);
        assertThat(stats.missCount()).isEqualTo(0L);
    }

    @Test
    void statsWithRecordStats() {
        Cache<String, String> cache = CacheBuilder.newBuilder().recordStats().build();

        cache.put("a", "1");
        assertThat(cache.getIfPresent("a")).isEqualTo("1");
        assertThat(cache.getIfPresent("b")).isNull();

        CacheStats stats = cache.stats();
        assertThat(stats.hitCount()).isEqualTo(1L);
        assertThat(stats.missCount()).isEqualTo(1L);
    }

}
