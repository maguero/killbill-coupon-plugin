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
import org.killbill.billing.plugin.coupon.api.model.CouponMapper;
import org.killbill.billing.plugin.coupon.CouponInternalTenantContext;
import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import java.util.UUID;

@UseStringTemplate3StatementLocator()
@RegisterMapper({CouponMapper.class, DefaultMapper.class})
public interface CouponSqlDao extends Transactional<CouponSqlDao> {

    // Coupons
    @SqlQuery
    public Coupon getCouponByCode(@Bind("couponCode") final String couponCode,
                                          @BindBean final CouponInternalTenantContext context);

    @SqlQuery
    public UUID getIdFromCode(@Bind("couponCode") final String couponCode,
                              @BindBean final CouponInternalTenantContext context);

    @SqlUpdate
    public void update(@BindBean final Coupon coupon,
                       @BindBean final CouponInternalTenantContext context);
}
