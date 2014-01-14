package rapaio.data.matrix;

import static rapaio.core.BaseMath.hypot;

/**
 * QR Decomposition.
 * <p/>
 * For an m-by-n rapaio.data.matrix A with m >= n, the QR decomposition is an m-by-n
 * orthogonal rapaio.data.matrix Q and an n-by-n upper triangular rapaio.data.matrix R so that
 * A = Q*R.
 * <p/>
 * The QR decompostion always exists, even if the rapaio.data.matrix does not have
 * full rank, so the constructor will never fail.  The primary use of the
 * QR decomposition is in the least squares solution of nonsquare systems
 * of simultaneous linear equations.  This will fail if isFullRank()
 * returns false.
 * <p/>
 * User: Aurelian Tutuianu <padreati@yahoo.com>
 */
public class QRDecomposition implements java.io.Serializable {

    private double[][] QR;
    private int m, n;
    private double[] Rdiag;

    public QRDecomposition(Matrix A) {
        // Initialize.
        QR = A.getArrayCopy();
        m = A.getRowDimension();
        n = A.getColumnDimension();
        Rdiag = new double[n];

        // Main loop.
        for (int k = 0; k < n; k++) {
            // Compute 2-norm of k-th column without under/overflow.
            double nrm = 0;
            for (int i = k; i < m; i++) {
                nrm = hypot(nrm, QR[i][k]);
            }

            if (nrm != 0.0) {
                // Form k-th Householder vector.
                if (QR[k][k] < 0) {
                    nrm = -nrm;
                }
                for (int i = k; i < m; i++) {
                    QR[i][k] /= nrm;
                }
                QR[k][k] += 1.0;

                // Apply transformation to remaining columns.
                for (int j = k + 1; j < n; j++) {
                    double s = 0.0;
                    for (int i = k; i < m; i++) {
                        s += QR[i][k] * QR[i][j];
                    }
                    s = -s / QR[k][k];
                    for (int i = k; i < m; i++) {
                        QR[i][j] += s * QR[i][k];
                    }
                }
            }
            Rdiag[k] = -nrm;
        }
    }

/* ------------------------
   Public Methods
 * ------------------------ */

    /**
     * Is the matrix full rank?
     *
     * @return true if R, and hence A, has full rank.
     */

    public boolean isFullRank() {
        for (int j = 0; j < n; j++) {
            if (Rdiag[j] == 0)
                return false;
        }
        return true;
    }

    /**
     * Return the Householder vectors
     *
     * @return Lower trapezoidal matrix whose columns define the reflections
     */

    public Matrix getH() {
        Matrix H = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    H.set(i, j, QR[i][j]);
                } else {
                    H.set(i, j, 0.0);
                }
            }
        }
        return H;
    }

    /**
     * Return the upper triangular factor
     *
     * @return R
     */

    public Matrix getR() {
        Matrix R = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    R.set(i, j, QR[i][j]);
                } else if (i == j) {
                    R.set(i, j, Rdiag[i]);
                } else {
                    R.set(i, j, 0.0);
                }
            }
        }
        return R;
    }

    /**
     * Generate and return the (economy-sized) orthogonal factor
     *
     * @return Q
     */

    public Matrix getQ() {
        Matrix Q = new Matrix(m, n);
        for (int k = n - 1; k >= 0; k--) {
            for (int i = 0; i < m; i++) {
                Q.set(i, k, 0.0);
            }
            Q.set(k, k, 1.0);
            for (int j = k; j < n; j++) {
                if (QR[k][k] != 0) {
                    double s = 0.0;
                    for (int i = k; i < m; i++) {
                        s += QR[i][k] * Q.get(i, j);
                    }
                    s = -s / QR[k][k];
                    for (int i = k; i < m; i++) {
                        Q.set(i, j, Q.get(i, j) + s * QR[i][k]);
                    }
                }
            }
        }
        return Q;
    }

    public Matrix getQR() {
        Matrix X = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                X.set(i, j, QR[i][j]);
            }
        }
        return X;
    }

    /**
     * Least squares solution of A*X = B
     *
     * @param B A Matrix with as many rows as A and any number of columns.
     * @return X that minimizes the two norm of Q*R*X-B.
     * @throws IllegalArgumentException Matrix row dimensions must agree.
     * @throws RuntimeException         Matrix is rank deficient.
     */

    public Matrix solve(Matrix B) {
        if (B.getRowDimension() != m) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (!this.isFullRank()) {
            throw new RuntimeException("Matrix is rank deficient.");
        }

        // Copy right hand side
        int nx = B.getColumnDimension();
        double[][] X = B.getArrayCopy();

        // Compute Y = transpose(Q)*B
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < nx; j++) {
                double s = 0.0;
                for (int i = k; i < m; i++) {
                    s += QR[i][k] * X[i][j];
                }
                s = -s / QR[k][k];
                for (int i = k; i < m; i++) {
                    X[i][j] += s * QR[i][k];
                }
            }
        }
        // Solve R*X = Y;
        for (int k = n - 1; k >= 0; k--) {
            for (int j = 0; j < nx; j++) {
                X[k][j] /= Rdiag[k];
            }
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < nx; j++) {
                    X[i][j] -= X[k][j] * QR[i][k];
                }
            }
        }
        return (new Matrix(X, 0, n - 1, 0, nx - 1));
    }
}