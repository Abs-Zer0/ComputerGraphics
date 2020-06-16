#ifndef MATRIX_H
#define MATRIX_H

namespace rc
{
    namespace math
    {
#include "vector.h"

        class Matrix3x3
        {
        public:
            Matrix3x3();

            Matrix3x3(double X11, double X12, double X13,
                      double X21, double X22, double X23,
                      double X31, double X32, double X33);

            Matrix3x3(double vals[]);

            ~Matrix3x3();

            double x11 = 0, x12 = 0, x13 = 0,
                   x21 = 0, x22 = 0, x23 = 0,
                   x31 = 0, x32 = 0, x33 = 0;

            inline Vector3d operator*(Vector3l vec);

            inline Vector3d operator*(Vector3d vec);

            inline Matrix3x3 operator*(Matrix3x3 mat);

            static Matrix3x3 eye();

            static Matrix3x3 zero();
        };
    } // namespace math
} // namespace rc

#endif // MATRIX_H
