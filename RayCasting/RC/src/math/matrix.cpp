#include "../../include/math/matrix.h"

namespace rc
{
    namespace math
    {
        Matrix3x3::Matrix3x3()
        {
        }

        Matrix3x3::Matrix3x3(double X11, double X12, double X13,
                             double X21, double X22, double X23,
                             double X31, double X32, double X33)
        {
            this->x11 = X11;
            this->x12 = X12;
            this->x13 = X13;
            this->x21 = X21;
            this->x22 = X22;
            this->x23 = X23;
            this->x31 = X31;
            this->x32 = X32;
            this->x33 = X33;
        }

        Matrix3x3::Matrix3x3(double vals[])
        {
            int count = sizeof(vals) / sizeof(double);

            if (count > 0)
                this->x11 = vals[0];
            if (count > 1)
                this->x12 = vals[1];
            if (count > 2)
                this->x13 = vals[2];
            if (count > 3)
                this->x21 = vals[3];
            if (count > 4)
                this->x22 = vals[4];
            if (count > 5)
                this->x23 = vals[5];
            if (count > 6)
                this->x31 = vals[6];
            if (count > 7)
                this->x32 = vals[7];
            if (count > 8)
                this->x33 = vals[8];
        }

        Matrix3x3::~Matrix3x3() {}

        inline Vector3d Matrix3x3::operator*(Vector3l vec)
        {
            double nx = (double)vec.x * this->x11 + (double)vec.y * this->x21 + (double)vec.z * this->x31;
            double ny = (double)vec.x * this->x12 + (double)vec.y * this->x22 + (double)vec.z * this->x32;
            double nz = (double)vec.x * this->x13 + (double)vec.y * this->x23 + (double)vec.z * this->x33;

            return Vector3d(nx, ny, nz);
        }

        inline Vector3d Matrix3x3::operator*(Vector3d vec)
        {
            double nx = vec.x * this->x11 + vec.y * this->x21 + vec.z * this->x31;
            double ny = vec.x * this->x12 + vec.y * this->x22 + vec.z * this->x32;
            double nz = vec.x * this->x13 + vec.y * this->x23 + vec.z * this->x33;

            return Vector3d(nx, ny, nz);
        }

        inline Matrix3x3 Matrix3x3::operator*(Matrix3x3 mat)
        {
            double nx11 = this->x11 * mat.x11 + this->x21 * mat.x12 + this->x31 * mat.x13;
            double nx12 = this->x12 * mat.x11 + this->x22 * mat.x12 + this->x32 * mat.x13;
            double nx13 = this->x13 * mat.x11 + this->x23 * mat.x12 + this->x33 * mat.x13;

            double nx21 = this->x11 * mat.x21 + this->x21 * mat.x22 + this->x31 * mat.x23;
            double nx22 = this->x12 * mat.x21 + this->x22 * mat.x22 + this->x32 * mat.x23;
            double nx23 = this->x13 * mat.x21 + this->x23 * mat.x22 + this->x33 * mat.x23;

            double nx31 = this->x11 * mat.x31 + this->x21 * mat.x32 + this->x31 * mat.x33;
            double nx32 = this->x12 * mat.x31 + this->x22 * mat.x32 + this->x32 * mat.x33;
            double nx33 = this->x13 * mat.x31 + this->x23 * mat.x32 + this->x33 * mat.x33;

            return Matrix3x3(nx11, nx12, nx13,
                             nx21, nx22, nx23,
                             nx31, nx32, nx33);
        }

        Matrix3x3 Matrix3x3::eye()
        {
            return Matrix3x3(1.0, 0.0, 0.0,
                             0.0, 1.0, 0.0,
                             0.0, 0.0, 1.0);
        }

        Matrix3x3 Matrix3x3::zero()
        {
            return Matrix3x3(0.0, 0.0, 0.0,
                             0.0, 0.0, 0.0,
                             0.0, 0.0, 0.0);
        }
    } // namespace math
} // namespace rc
