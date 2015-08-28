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

import org.killbill.billing.plugin.coupon.api.model.Coupon;
import org.killbill.billing.util.callcontext.TenantContext;

import java.util.UUID;

public interface CouponDao {

    // Coupons table

    Coupon getCouponByCode(String couponCode, TenantContext context);

    UUID getIdFromCode(String couponCode, TenantContext context);

    void update(Coupon coupon, TenantContext context);

    // Sources table

//    Integer getSourceId(String source, TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    String getSource(Integer sourceId, TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    BiMap<Integer, String> getSources(TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    int getOrAddSource(String source, CallContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    // Event categories table
//
//    Integer getEventCategoryId(String eventCategory, TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    String getEventCategory(Integer eventCategoryId, TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    BiMap<Integer, String> getEventCategories(TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    int getOrAddEventCategory(String eventCategory, CallContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    // Metrics table
//
//    Integer getMetricId(int eventCategory, String metric, TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    CategoryRecordIdAndMetric getCategoryIdAndMetric(Integer metricId, TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    BiMap<Integer, CategoryRecordIdAndMetric> getMetrics(TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    int getOrAddMetric(Integer eventCategoryId, String metric, CallContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    // Timelines tables
//
//    Long insertTimelineChunk(TimelineChunk timelineChunk, CallContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    void getSamplesBySourceIdsAndMetricIds(List<Integer> sourceIds,
//                                           @Nullable List<Integer> metricIds,
//                                           DateTime startTime,
//                                           DateTime endTime,
//                                           TimelineChunkConsumer chunkConsumer,
//                                           TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
//
//    Integer insertLastStartTimes(StartTimes startTimes, CallContext context);
//
//    StartTimes getLastStartTimes(TenantContext context);
//
//    void deleteLastStartTimes(CallContext context);
//
//    void bulkInsertTimelineChunks(List<TimelineChunk> timelineChunkList, CallContext context);
//
//    void test(TenantContext context) throws UnableToObtainConnectionException, CallbackFailedException;
}
