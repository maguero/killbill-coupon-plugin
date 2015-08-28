/*
 * Copyright 2014-2015 Groupon, Inc
 * Copyright 2014-2015 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
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

package org.killbill.billing.plugin.coupon.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.killbill.billing.jaxrs.json.JsonBase;
import org.killbill.billing.plugin.coupon.api.model.Coupon;
import org.killbill.billing.plugin.coupon.api.model.CouponData;

public class CouponJson extends JsonBase {

    private final String couponCode;
    private final String couponName;

    public CouponJson(final Coupon coupon) {
        this.couponCode = coupon.getCouponCode();
        this.couponName = coupon.getCouponName();
    }

    @JsonCreator
    public CouponJson(@JsonProperty("couponCode") final String couponCode,
                      @JsonProperty("couponName") final String couponName) {
        this.couponCode = couponCode;
        this.couponName = couponName;
    }

    public CouponData toCouponData() {
        return new CouponData() {

            @Override
            public String getCouponCode() {
                return couponCode;
            }

            @Override
            public String getCouponName() {
                return couponName;
            }
        };
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getCouponName() {
        return couponName;
    }

    @Override
    public String toString() {
        return "CouponJson{" +
               "couponCode='" + couponCode + '\'' +
               ", couponName='" + couponName + '\'' +
               '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final CouponJson that = (CouponJson) o;

        if (couponCode != null ? !couponCode.equals(that.couponCode) : that.couponCode != null) {
            return false;
        }
        if (couponName != null ? !couponName.equals(that.couponName) : that.couponName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = couponCode != null ? couponCode.hashCode() : 0;
        result = 31 * result + (couponName != null ? couponName.hashCode() : 0);
        return result;
    }
}
