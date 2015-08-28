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

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.AbstractModule;
import org.killbill.billing.plugin.coupon.CouponConfig;
import org.killbill.billing.plugin.coupon.CouponService;
import org.killbill.billing.plugin.coupon.api.user.CouponUserApi;
import org.killbill.billing.plugin.coupon.api.user.DefaultCouponUserApi;
import org.killbill.billing.plugin.coupon.dao.CouponDao;
import org.killbill.billing.plugin.coupon.jaxrs.resources.CouponResource;
import org.killbill.commons.jdbi.argument.DateTimeArgumentFactory;
import org.killbill.commons.jdbi.argument.DateTimeZoneArgumentFactory;
import org.killbill.commons.jdbi.argument.UUIDArgumentFactory;
import org.skife.config.ConfigSource;
import org.skife.config.ConfigurationObjectFactory;
import org.skife.config.SimplePropertyConfigSource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.IDBI;

import javax.sql.DataSource;

public class CouponModule extends AbstractModule {

    private final ConfigSource configSource;
    private final DataSource dataSource;

    public CouponModule(final DataSource dataSource) {
        this(new SimplePropertyConfigSource(System.getProperties()), dataSource);
    }

    public CouponModule(final ConfigSource configSource, final DataSource dataSource) {
        this.configSource = configSource;
        this.dataSource = dataSource;
    }

    protected CouponConfig installConfig() {
        final CouponConfig config = new ConfigurationObjectFactory(configSource).build(CouponConfig.class);
        bind(CouponConfig.class).toInstance(config);

        return config;
    }

    protected void configureDao() {
        bind(IDBI.class).toInstance(getDBI());
        bind(CouponDao.class).toProvider(DefaultCouponDaoProvider.class).asEagerSingleton();
    }

    @VisibleForTesting
    public IDBI getDBI() {
        final DBI dbi = new DBI(dataSource);
        dbi.registerArgumentFactory(new DateTimeArgumentFactory());
        dbi.registerArgumentFactory(new DateTimeZoneArgumentFactory());
        dbi.registerArgumentFactory(new UUIDArgumentFactory());
        return dbi;
    }

    protected void installCouponUserApi() {
        bind(CouponUserApi.class).to(DefaultCouponUserApi.class).asEagerSingleton();
        bind(CouponResource.class).asEagerSingleton();
    }


    protected void installCouponService() {
        bind(CouponService.class).asEagerSingleton();
    }

    @Override
    protected void configure() {
        final CouponConfig config = installConfig();
        installCouponService();
        configureDao();

        installCouponUserApi();
    }
}
