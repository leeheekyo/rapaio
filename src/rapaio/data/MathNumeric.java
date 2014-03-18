/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 *    Copyright 2013 Aurelian Tutuianu
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package rapaio.data;

import rapaio.core.stat.Mean;
import rapaio.core.stat.Variance;
import rapaio.data.collect.VIterator;

/**
 * User: Aurelian Tutuianu <padreati@yahoo.com>
 */
public final class MathNumeric {

    public static Numeric sum(final Numeric num) {
        double sum = 0;
        for (int i = 0; i < num.rowCount(); i++) {
            sum += num.value(i);
        }
        return new Numeric(1, 1, sum);
    }

    public static Numeric mean(final Numeric num) {
        return new Numeric(1, 1, new Mean(num).getValue());
    }

    public static Numeric sd(final Numeric num) {
        return new Numeric(1, 1, StrictMath.sqrt(new Variance(num).getValue()));
    }

    public static Numeric var(final Numeric num) {
        return new Numeric(1, 1, new Variance(num).getValue());
    }

    public static Numeric plus(final Numeric... nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (len < nums[i].rowCount())
                len = nums[i].rowCount();
        }
        Numeric c = new Numeric(len, len, 0);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < len; j++) {
                c.setValue(j, c.value(j) + nums[i].value(j % nums[i].rowCount()));
            }
        }
        return c;
    }

    public static Numeric minus(final Numeric a, final Numeric b) {
        Numeric c = new Numeric();
        VIterator aIt = a.cycleIterator(StrictMath.max(a.rowCount(), b.rowCount()));
        VIterator bIt = b.cycleIterator(StrictMath.max(a.rowCount(), b.rowCount()));
        while (aIt.next() && bIt.next()) {
            c.addValue(aIt.getValue() - bIt.getValue());
        }
        return c;
    }

    public static Numeric dot(final Numeric a, final Numeric b) {
        final int len = StrictMath.max(a.rowCount(), b.rowCount());
        Numeric c = new Numeric(len);
        for (int i = 0; i < len; i++) {
            c.setValue(i, a.value(i % a.rowCount()) * b.value(i % b.rowCount()));
        }
        return c;
    }

    public static Numeric dotSum(final Numeric a, final Numeric b) {
        final int len = StrictMath.max(a.rowCount(), b.rowCount());
        double sum = 0;
        for (int i = 0; i < len; i++) {
            sum += a.value(i % a.rowCount()) * b.value(i % b.rowCount());
        }
        Numeric c = new Numeric();
        c.addValue(sum);
        return c;
    }

    public static Numeric div(final Numeric a, final Numeric b) {
        final int len = StrictMath.max(a.rowCount(), b.rowCount());
        Numeric c = new Numeric(len);
        for (int i = 0; i < len; i++) {
            c.setValue(i, a.value(i % a.rowCount()) / b.value(i % b.rowCount()));
        }
        return c;
    }

    public static Numeric scale(final Numeric a) {
        final Numeric v = new Numeric(a.rowCount());
        double mean = mean(a).value(0);
        double sd = sd(a).value(0);
        for (int i = 0; i < v.rowCount(); i++) {
            v.setValue(i, (a.value(i) - mean) / sd);
        }
        return v;
    }

    public static Numeric pow(final Vector a, double pow) {
        Numeric v = new Numeric();
        for (int i = 0; i < a.rowCount(); i++) {
            v.addValue(StrictMath.pow(a.value(i), pow));
        }
        return v;
    }

    public static Numeric ln(final Vector a, double shift) {
        Numeric v = new Numeric();
        for (int i = 0; i < a.rowCount(); i++) {
            v.addValue(StrictMath.log(a.value(i) + shift));
        }
        return v;
    }

}