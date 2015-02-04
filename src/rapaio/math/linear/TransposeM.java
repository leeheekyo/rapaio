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

package rapaio.math.linear;

/**
 * Created by <a href="mailto:padreati@yahoo.com">Aurelian Tutuianu</a> at 2/4/15.
 */
public class TransposeM implements M {
    private final M ref;

    TransposeM(M ref) {
        this.ref = ref;
    }

    @Override
    public int rows() {
        return ref.cols();
    }

    @Override
    public int cols() {
        return ref.rows();
    }

    @Override
    public double get(int i, int j) {
        return ref.get(j, i);
    }

    @Override
    public void set(int i, int j, double value) {
        ref.set(j, i, value);
    }

    @Override
    public M t() {
        return ref;
    }
}