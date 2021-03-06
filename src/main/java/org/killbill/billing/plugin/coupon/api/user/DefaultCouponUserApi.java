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

package org.killbill.billing.plugin.coupon.api.user;

import org.killbill.billing.plugin.coupon.api.exception.CouponApiException;
import org.killbill.billing.plugin.coupon.api.model.Coupon;
import org.killbill.billing.plugin.coupon.dao.CouponDao;
import org.killbill.billing.util.callcontext.TenantContext;

import javax.inject.Inject;

public class DefaultCouponUserApi implements CouponUserApi {

    private static final String AGGREGATE_METRIC_NAME = "__AGGREGATE__";

    private final CouponDao couponDao;

    @Inject
    public DefaultCouponUserApi(final CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    @Override
    public Coupon getCouponByCode(String couponCode, TenantContext context) throws CouponApiException {
        return couponDao.getCouponByCode(couponCode, context);
    }

//    @Override
//    public void getUsage(final OutputStream outputStream, final TimeAggregationMode timeAggregationMode,
//                         final String source, final Collection<String> categories,
//                         final DateTime fromTimestamp, final DateTime toTimestamp, final TenantContext context) throws IOException {
//        final ImmutableMap.Builder<String, Collection<String>> metricsPerCategory = new Builder<String, Collection<String>>();
//        for (final String category : categories) {
//            metricsPerCategory.put(category, ImmutableList.<String>of(AGGREGATE_METRIC_NAME));
//        }
//
//        getUsage(outputStream, timeAggregationMode, source, metricsPerCategory.build(), fromTimestamp, toTimestamp, context);
//    }
//
//    @Override
//    public void getUsage(final OutputStream outputStream, final TimeAggregationMode timeAggregationMode,
//                         final String source, final Map<String, Collection<String>> metricsPerCategory,
//                         final DateTime fromTimestamp, final DateTime toTimestamp, final TenantContext context) throws IOException {
//        final JsonSamplesOutputer outputerJson = new AccumulatingJsonSamplesOutputer(timeAggregationMode, timelineEventHandler, couponDao, context);
//        outputerJson.output(outputStream, ImmutableList.<String>of(source), metricsPerCategory, fromTimestamp, toTimestamp);
//    }
//
//    @Override
//    public void getUsage(final OutputStream outputStream, final DecimationMode decimationMode, @Nullable final Integer outputCount,
//                         final String source, final Map<String, Collection<String>> metricsPerCategory,
//                         final DateTime fromTimestamp, final DateTime toTimestamp, final TenantContext context) throws IOException {
//        final JsonSamplesOutputer outputerJson = new DecimatingJsonSamplesOutputer(decimationMode, outputCount, timelineEventHandler, couponDao, context);
//        outputerJson.output(outputStream, ImmutableList.<String>of(source), metricsPerCategory, fromTimestamp, toTimestamp);
//    }
//
//    @Override
//    public void getUsage(final OutputStream outputStream, final String source, final Collection<String> categories,
//                         final DateTime fromTimestamp, final DateTime toTimestamp, final TenantContext context) throws IOException {
//        final ImmutableMap.Builder<String, Collection<String>> metricsPerCategory = new Builder<String, Collection<String>>();
//        for (final String category : categories) {
//            metricsPerCategory.put(category, ImmutableList.<String>of(AGGREGATE_METRIC_NAME));
//        }
//
//        getUsage(outputStream, source, metricsPerCategory.build(), fromTimestamp, toTimestamp, context);
//    }
//
//    @Override
//    public void getUsage(final OutputStream outputStream, final String source, final Map<String, Collection<String>> metricsPerCategory,
//                         final DateTime fromTimestamp, final DateTime toTimestamp, final TenantContext context) throws IOException {
//        final JsonSamplesOutputer outputerJson = new DefaultJsonSamplesOutputer(timelineEventHandler, couponDao, context);
//        outputerJson.output(outputStream, ImmutableList.<String>of(source), metricsPerCategory, fromTimestamp, toTimestamp);
//    }
//
//    @Override
//    public void incrementUsage(final String source, final String categoryName, final String metricName,
//                               final DateTime timestamp, final CallContext context) {
//        recordUsage(source,
//                    ImmutableMap.<String, Map<String, Object>>of(categoryName, ImmutableMap.<String, Object>of(metricName, (short) 1)),
//                    timestamp,
//                    context);
//    }
//
//    @Override
//    public void incrementUsageAndAggregate(final String source, final String categoryName, final String metricName,
//                                           final DateTime timestamp, final CallContext context) {
//        recordUsage(source,
//                    ImmutableMap.<String, Map<String, Object>>of(categoryName, ImmutableMap.<String, Object>of(metricName, (short) 1, AGGREGATE_METRIC_NAME, (short) 1)),
//                    timestamp,
//                    context);
//    }
//
//    @Override
//    public void recordUsage(final String source, final Map<String, Map<String, Object>> samplesForCategoriesAndMetrics,
//                            final DateTime timestamp, final CallContext context) {
//        for (final String category : samplesForCategoriesAndMetrics.keySet()) {
//            timelineEventHandler.record(source, category, timestamp, samplesForCategoriesAndMetrics.get(category), context);
//        }
//    }
}
