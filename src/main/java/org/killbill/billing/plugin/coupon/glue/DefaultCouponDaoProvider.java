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

package org.killbill.billing.plugin.coupon.glue;

import com.google.inject.Inject;
import org.killbill.billing.plugin.coupon.dao.CouponDao;
import org.killbill.billing.plugin.coupon.dao.DefaultCouponDao;
import org.skife.jdbi.v2.IDBI;

import javax.inject.Provider;

public class DefaultCouponDaoProvider implements Provider<CouponDao> {

    private final IDBI dbi;

    @Inject
    public DefaultCouponDaoProvider(final IDBI dbi) {
        this.dbi = dbi;
    }

    @Override
    public CouponDao get() {
        return new DefaultCouponDao(dbi);
    }
}
