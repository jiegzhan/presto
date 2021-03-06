/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.plugin.hive.metastore.glue;

import com.amazonaws.services.glue.model.PartitionInput;
import com.amazonaws.services.glue.model.TableInput;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.prestosql.plugin.hive.metastore.HiveColumnStatistics;
import io.prestosql.plugin.hive.metastore.Partition;
import io.prestosql.plugin.hive.metastore.Table;
import io.prestosql.spi.PrestoException;
import io.prestosql.spi.statistics.ColumnStatisticType;
import io.prestosql.spi.type.Type;

import java.util.Map;
import java.util.Set;

import static io.prestosql.spi.StandardErrorCode.NOT_SUPPORTED;

public class DisabledGlueColumnStatisticsProvider
        implements GlueColumnStatisticsProvider
{
    @Override
    public Set<ColumnStatisticType> getSupportedColumnStatistics(Type type)
    {
        return ImmutableSet.of();
    }

    @Override
    public Map<String, HiveColumnStatistics> getTableColumnStatistics(Table table)
    {
        return ImmutableMap.of();
    }

    @Override
    public Map<String, HiveColumnStatistics> getPartitionColumnStatistics(Partition partition)
    {
        return ImmutableMap.of();
    }

    @Override
    public void updateTableColumnStatistics(TableInput table, Map<String, HiveColumnStatistics> columnStatistics)
    {
        if (!columnStatistics.isEmpty()) {
            throw new PrestoException(NOT_SUPPORTED, "Glue metastore column level statistics are disabled");
        }
    }

    @Override
    public void updatePartitionStatistics(PartitionInput partition, Map<String, HiveColumnStatistics> columnStatistics)
    {
        if (!columnStatistics.isEmpty()) {
            throw new PrestoException(NOT_SUPPORTED, "Glue metastore column level statistics are disabled");
        }
    }
}
