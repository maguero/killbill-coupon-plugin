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

package org.killbill.billing.plugin.coupon.osgi;

import org.killbill.billing.plugin.coupon.CouponService;
import org.killbill.billing.plugin.coupon.glue.CouponModule;
import org.killbill.killbill.osgi.libs.killbill.KillbillActivatorBase;
import org.killbill.killbill.osgi.libs.killbill.OSGIKillbillEventDispatcher.OSGIKillbillEventHandler;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

public class CouponActivator extends KillbillActivatorBase {

    private CouponService couponService = null;

    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);

        final Injector injector = Guice.createInjector(Stage.PRODUCTION, new CouponModule(dataSource.getDataSource()));
        couponService = (CouponService) injector.getInstance(CouponService.class);
        couponService.start();
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        super.stop(context);

        if (couponService != null) {
            couponService.stop();
        }
    }

    @Override
    public OSGIKillbillEventHandler getOSGIKillbillEventHandler() {
        return null;
    }
}
