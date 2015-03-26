/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 *    Copyright 2013 Aurelian Tutuianu
 *    Copyright 2014 Aurelian Tutuianu
 *    Copyright 2015 Aurelian Tutuianu
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

package rapaio.math.linear;

import rapaio.data.Frame;
import rapaio.data.Var;
import rapaio.math.linear.impl.SolidM;
import rapaio.math.linear.impl.SolidV;

import java.util.function.BiFunction;

/**
 * Created by <a href="mailto:padreati@yahoo.com">Aurelian Tutuianu</a> at 2/6/15.
 */
public final class LA {

    /**
     * Builds a new 0 filled matrix with given rows and cols
     *
     * @param rowCount number of rows
     * @param colCount number of columns
     * @return new matrix object
     */
    public static M newMEmpty(int rowCount, int colCount) {
        return new SolidM(rowCount, colCount);
    }

    public static M newMWrapOf(int rowCount, int colCount, double... values) {
        return new SolidM(rowCount, colCount, values);
    }

    public static M newCopyOf(double[][] source) {
        return newMCopyOf(source, 0, source.length, 0, source[0].length);
    }

    public static M newMCopyOf(double[][] source, int mFirst, int mLast, int nFirst, int nLast) {
        M mm = new SolidM(mLast - mFirst, nLast - nFirst);
        for (int i = mFirst; i < mLast; i++) {
            for (int j = nFirst; j < nLast; j++) {
                mm.set(i, j, source[i][j]);
            }
        }
        return mm;
    }

    /**
     * Builds a new matrix with given rows and cols, fillen with given value
     *
     * @param rowCount number of rows
     * @param colCount number of columns
     * @param fill     initial value for all matrix cells
     * @return new matrix object
     */
    public static M newMFill(int rowCount, int colCount, double fill) {
        if (fill == 0) {
            return newMEmpty(rowCount, colCount);
        }
        M m = new SolidM(rowCount, colCount);
        for (int i = 0; i < m.rowCount(); i++) {
            for (int j = 0; j < m.colCount(); j++) {
                m.set(i, j, fill);
            }
        }
        return m;
    }

    public static M newMFill(int rowCount, int colCount, BiFunction<Integer, Integer, Double> f) {
        M m = new SolidM(rowCount, colCount);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                m.set(i, j, f.apply(i, j));
            }
        }
        return m;
    }

    public static M newMCopyOf(Frame df) {
        M m = new SolidM(df.rowCount(), df.varCount());
        for (int i = 0; i < df.rowCount(); i++) {
            for (int j = 0; j < df.varCount(); j++) {
                m.set(i, j, df.value(i, j));
            }
        }
        return m;
    }

    public static V newVCopyOf(Var var) {
        return new SolidV(var);
    }

    public static V newVEmpty(int rows) {
        return new SolidV(rows);
    }

    public static M newId(int n) {
        M id = LA.newMEmpty(n, n);
        for (int i = 0; i < n; i++) {
            id.set(i, i, 1.0);
        }
        return id;
    }
}
