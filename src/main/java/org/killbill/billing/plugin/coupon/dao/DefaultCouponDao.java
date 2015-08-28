/*
 * Copyright 2010-2014 Ning, Inc.
 * Copyright 2014 The Billing Project, LLC
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.plugin.coupon.dao;

import com.google.common.base.Joiner;
import com.google.inject.Inject;
import org.killbill.billing.plugin.coupon.api.model.Coupon;
import org.killbill.billing.plugin.coupon.CouponInternalCallContext;
import org.killbill.billing.plugin.coupon.CouponInternalTenantContext;
import org.killbill.billing.util.callcontext.CallContext;
import org.killbill.billing.util.callcontext.TenantContext;
import org.skife.jdbi.v2.IDBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Callable;

public class DefaultCouponDao implements CouponDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultCouponDao.class);
    private static final Joiner JOINER = Joiner.on(",");

    private final IDBI dbi;
    private final CouponSqlDao delegate;

    @Inject
    public DefaultCouponDao(final IDBI dbi) {
        this.dbi = dbi;
        this.delegate = dbi.onDemand(CouponSqlDao.class);
    }

    @Override
    public Coupon getCouponByCode(final String couponCode, final TenantContext context) {
        return delegate.getCouponByCode(couponCode, createInternalTenantContext(context));
    }

    @Override
    public UUID getIdFromCode(String couponCode, TenantContext context) {
        return null;
    }

    @Override
    public void update(Coupon coupon, TenantContext context) {

    }

//    @Override
//    public String getSource(final Integer sourceId, final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        return delegate.getSourceName(sourceId, createInternalTenantContext(context));
//    }
//
//    @Override
//    public Integer getSourceId(final String source, final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        return delegate.getSourceRecordId(source, createInternalTenantContext(context));
//    }
//
//    @Override
//    public BiMap<Integer, String> getSources(final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        final HashBiMap<Integer, String> accumulator = HashBiMap.create();
//        for (final Map<String, Object> metric : delegate.getSources(createInternalTenantContext(context))) {
//            accumulator.put(Integer.valueOf(metric.get("record_id").toString()), metric.get("source").toString());
//        }
//        return accumulator;
//    }
//
//    @Override
//    public int getOrAddSource(final String source, final CallContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//
//        final Integer result = delegate.inTransaction(new Transaction<Integer, TimelineSqlDao>() {
//
//            @Override
//            public Integer inTransaction(final TimelineSqlDao transactional, final TransactionStatus status) throws Exception {
//                return getOrAddWithRetry(new Callable<Integer>() {
//                    @Override
//                    public Integer call() throws Exception {
//                        final CouponInternalTenantContext internalTenantContext = createInternalTenantContext(context);
//                        final CouponInternalCallContext internalCallContext = createInternalCallContext(context);
//                        Integer sourceId = transactional.getSourceRecordId(source, internalTenantContext);
//                        if (sourceId == null) {
//                            transactional.addSource(source, internalCallContext);
//                            sourceId = transactional.getSourceRecordId(source, internalTenantContext);
//                        }
//                        return sourceId;
//                    }
//                });
//            }
//        });
//        return result;
//    }
//
//    @Override
//    public Integer getEventCategoryId(final String eventCategory, final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        return delegate.getCategoryRecordId(eventCategory, createInternalTenantContext(context));
//    }
//
//    @Override
//    public String getEventCategory(final Integer eventCategoryId, final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        return delegate.getCategory(eventCategoryId, createInternalTenantContext(context));
//    }
//
//    @Override
//    public BiMap<Integer, String> getEventCategories(final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        final HashBiMap<Integer, String> accumulator = HashBiMap.create();
//        for (final Map<String, Object> eventCategory : delegate.getCategories(createInternalTenantContext(context))) {
//            accumulator.put(Integer.valueOf(eventCategory.get("record_id").toString()), eventCategory.get("category").toString());
//        }
//        return accumulator;
//    }
//
//    @Override
//    public int getOrAddEventCategory(final String eventCategory, final CallContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//
//        final Integer result = delegate.inTransaction(new Transaction<Integer, TimelineSqlDao>() {
//
//            @Override
//            public Integer inTransaction(final TimelineSqlDao transactional, final TransactionStatus status) throws Exception {
//                return getOrAddWithRetry(new Callable<Integer>() {
//                    @Override
//                    public Integer call() throws Exception {
//                        final CouponInternalTenantContext internalTenantContext = createInternalTenantContext(context);
//                        final CouponInternalCallContext internalCallContext = createInternalCallContext(context);
//                        Integer eventCategoryId = transactional.getCategoryRecordId(eventCategory, internalTenantContext);
//                        if (eventCategoryId == null) {
//                            transactional.addCategory(eventCategory, internalCallContext);
//                            eventCategoryId = transactional.getCategoryRecordId(eventCategory, internalTenantContext);
//                        }
//                        return eventCategoryId;
//                    }
//                });
//            }
//        });
//        return result;
//    }
//
//
//    @Override
//    public Integer getMetricId(final int eventCategoryId, final String metric, final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        return delegate.getMetricRecordId(eventCategoryId, metric, createInternalTenantContext(context));
//    }
//
//    @Override
//    public CategoryRecordIdAndMetric getCategoryIdAndMetric(final Integer metricId, final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        return delegate.getCategoryRecordIdAndMetric(metricId, createInternalTenantContext(context));
//    }
//
//    @Override
//    public BiMap<Integer, CategoryRecordIdAndMetric> getMetrics(final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        final HashBiMap<Integer, CategoryRecordIdAndMetric> accumulator = HashBiMap.create();
//        for (final Map<String, Object> metricInfo : delegate.getMetrics(createInternalTenantContext(context))) {
//            accumulator.put(Integer.valueOf(metricInfo.get("record_id").toString()),
//                            new CategoryRecordIdAndMetric((Integer) metricInfo.get("category_record_id"), metricInfo.get("metric").toString()));
//        }
//        return accumulator;
//    }
//
//    @Override
//    public synchronized int getOrAddMetric(final Integer eventCategoryId, final String metric, final CallContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//
//        final Integer result = delegate.inTransaction(new Transaction<Integer, TimelineSqlDao>() {
//
//            @Override
//            public Integer inTransaction(final TimelineSqlDao transactional, final TransactionStatus status) throws Exception {
//                return getOrAddWithRetry(new Callable<Integer>() {
//                    @Override
//                    public Integer call() throws Exception {
//                        final CouponInternalTenantContext internalTenantContext = createInternalTenantContext(context);
//                        final CouponInternalCallContext internalCallContext = createInternalCallContext(context);
//                        Integer metricId = transactional.getMetricRecordId(eventCategoryId, metric, internalTenantContext);
//                        if (metricId == null) {
//                            transactional.addMetric(eventCategoryId, metric, internalCallContext);
//                            metricId = transactional.getMetricRecordId(eventCategoryId, metric, internalTenantContext);
//                        }
//                        return metricId;
//                    }
//                });
//            }
//        });
//        return result;
//    }
//
//    @Override
//    public Long insertTimelineChunk(final TimelineChunk timelineChunk, final CallContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//
//        final Long result = delegate.inTransaction(new Transaction<Long, TimelineSqlDao>() {
//            @Override
//            public Long inTransaction(final TimelineSqlDao transactional, final TransactionStatus status) throws Exception {
//                final CouponInternalTenantContext internalTenantContext = createInternalTenantContext(context);
//                final CouponInternalCallContext internalCallContext = createInternalCallContext(context);
//                transactional.insertTimelineChunk(timelineChunk, internalCallContext);
//                final long timelineChunkId = transactional.getLastInsertedRecordId(internalTenantContext);
//                return timelineChunkId;
//            }
//        });
//        return result;
//    }
//
//    @Override
//    public void getSamplesBySourceIdsAndMetricIds(final List<Integer> sourceIdList,
//                                                  @Nullable final List<Integer> metricIdList,
//                                                  final DateTime startTime,
//                                                  final DateTime endTime,
//                                                  final TimelineChunkConsumer chunkConsumer,
//                                                  final TenantContext context) {
//        if (sourceIdList.size() == 0) {
//            return;
//        }
//
//        dbi.withHandle(new HandleCallback<Void>() {
//            @Override
//            public Void withHandle(final Handle handle) throws Exception {
//                handle.setStatementLocator(new StringTemplate3StatementLocator(TimelineSqlDao.class));
//
//                ResultIterator<TimelineChunk> iterator = null;
//                try {
//                    final Query<Map<String, Object>> query = handle
//                            .createQuery("getSamplesBySourceRecordIdsAndMetricRecordIds")
//                            .bind("startTime", DateTimeUtils.unixSeconds(startTime))
//                            .bind("endTime", DateTimeUtils.unixSeconds(endTime))
//                            .bind("tenantRecordId", createInternalTenantContext(context).getTenantRecordId())
//                            .define("sourceIds", JOINER.join(sourceIdList));
//
//                    if (metricIdList != null && !metricIdList.isEmpty()) {
//                        query.define("metricIds", JOINER.join(metricIdList));
//                    }
//
//                    iterator = query
//                            .map(timelineChunkMapper)
//                            .iterator();
//
//                    while (iterator.hasNext()) {
//                        chunkConsumer.processTimelineChunk(iterator.next());
//                    }
//                    return null;
//                } finally {
//                    if (iterator != null) {
//                        try {
//                            iterator.close();
//                        } catch (Exception e) {
//                            log.error("Exception closing TimelineChunkAndTimes iterator for sourceIds {} and metricIds {}", sourceIdList, metricIdList);
//                        }
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public Integer insertLastStartTimes(final StartTimes startTimes, final CallContext context) {
//        return delegate.insertLastStartTimes(startTimes, createInternalCallContext(context));
//    }
//
//    @Override
//    public StartTimes getLastStartTimes(final TenantContext context) {
//        return delegate.getLastStartTimes(createInternalTenantContext(context));
//    }
//
//    @Override
//    public void deleteLastStartTimes(final CallContext context) {
//        delegate.deleteLastStartTimes(createInternalCallContext(context));
//    }
//
//    @Override
//    public void test(final TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException {
//        delegate.test(createInternalTenantContext(context));
//    }
//
//    @Override
//    public void bulkInsertTimelineChunks(final List<TimelineChunk> timelineChunkList, final CallContext context) {
//        delegate.bulkInsertTimelineChunks(timelineChunkList.iterator(), createInternalCallContext(context));
//    }

    private CouponInternalTenantContext createInternalTenantContext(final TenantContext context) {
        return new CouponInternalTenantContext(/* TODO */);
    }

    private CouponInternalCallContext createInternalCallContext(final CallContext context) {
        return new CouponInternalCallContext(context, 0L, CouponInternalTenantContext.INTERNAL_TENANT_RECORD_ID /* TODO */);
    }

    private <T> T getOrAddWithRetry(final Callable<T> task) throws Exception {
        int retry = 1;
        Exception lastException = null;
        do {
            try {
                return task.call();
            } catch (Exception e) {
                //
                // If we have two transaction that occurs at the time and try to insert
                // both the same key, one of the transaction will rollback and that code will retry
                // and (should) succeed because this time key exists and caller will first do a get.
                //
                lastException = e;
            }
        } while (retry-- > 0);
        throw lastException;
    }

}
