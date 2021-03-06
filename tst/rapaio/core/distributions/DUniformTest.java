/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 *    Copyright 2013 Aurelian Tutuianu
 *    Copyright 2014 Aurelian Tutuianu
 *    Copyright 2015 Aurelian Tutuianu
 *    Copyright 2016 Aurelian Tutuianu
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
 *
 */

package rapaio.core.distributions;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by <a href="mailto:padreati@yahoo.com">Aurelian Tutuianu</a> at 11/4/14.
 */
public class DUniformTest {

    private static final double TOL = 1e-12;

    @Test
    public void testOtherFeatures() {
        DUniform du = new DUniform(1, 6);

        Assert.assertEquals(true, du.discrete());
        Assert.assertEquals(1, du.a(), TOL);
        Assert.assertEquals(6, du.b(), TOL);
        Assert.assertEquals(3.5, du.mean(), TOL);
        Assert.assertEquals(1, du.min(), TOL);
        Assert.assertEquals(6, du.max(), TOL);
        Assert.assertEquals(Double.NaN, du.mode(), TOL);
        Assert.assertEquals(2.9166666666666665, du.var(), TOL);
        Assert.assertEquals(0, du.skewness(), TOL);
        Assert.assertEquals(-1.2685714285714285, du.kurtosis(), TOL);
        Assert.assertEquals(1.791759469228055, du.entropy(), TOL);

        Assert.assertEquals(8, new DUniform(8, 8).quantile(0.7), TOL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowQuantile() {
        new DUniform(1, 6).quantile(-0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHighQuantile() {
        new DUniform(1, 6).quantile(1.1);
    }

    @Test
    public void testDUniformPdf() {
        DUniform u = new DUniform(0, 5);

        assertEquals("DUniform(a=0,b=5)", u.name());

        assertEquals(0.0, u.pdf(-1), TOL);
        assertEquals(0.0, u.pdf(0.5), TOL);
        assertEquals(1 / 6.0, u.pdf(0), TOL);
        assertEquals(1 / 6.0, u.pdf(3), TOL);
        assertEquals(1 / 6.0, u.pdf(5), TOL);
        assertEquals(0.0, u.pdf(6), TOL);
    }

    @Test
    public void testDUniformCdf() {
        DUniform u = new DUniform(0, 5);

        assertEquals("DUniform(a=0,b=5)", u.name());

        assertEquals(0.0, u.cdf(-1), TOL);
        assertEquals(1 / 6.0, u.cdf(0.5), TOL);
        assertEquals(1 / 6.0, u.cdf(0), TOL);
        assertEquals(4 / 6.0, u.cdf(3), TOL);
        assertEquals(1.0, u.cdf(5), TOL);
        assertEquals(1.0, u.cdf(6), TOL);
    }

    @Test
    public void testDUniformQuantile() {
        DUniform u = new DUniform(0, 5);

        assertEquals("DUniform(a=0,b=5)", u.name());

        assertEquals(0, u.quantile(1 / 6.0), TOL);
        assertEquals(1, u.quantile(1.2 / 6.0), TOL);
        assertEquals(1, u.quantile(2 / 6.0), TOL);
        assertEquals(2, u.quantile(3 / 6.0), TOL);
        assertEquals(3, u.quantile(4 / 6.0), TOL);
        assertEquals(4, u.quantile(5 / 6.0), TOL);
        assertEquals(5, u.quantile(6 / 6.0), TOL);
    }
}
