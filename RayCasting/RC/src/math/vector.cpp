#include "../../include/math/vector.h"

namespace rc
{
    namespace math
    {
#pragma region VECTOR2L
        Vector2l::Vector2l(long value)
        {
            this->x = value;
            this->y = value;
        }

        Vector2l::Vector2l(Vector2l &other)
        {
            this->x = other.x;
            this->y = other.y;
        }

        Vector2l::Vector2l(Vector2d &other)
        {
            this->x = (long)other.x;
            this->y = (long)other.y;
        }

        Vector2l::Vector2l() {}

        Vector2l::~Vector2l() {}

        inline Vector2l Vector2l::operator+(Vector2l other)
        {
            return Vector2l(this->x + other.x, this->y + other.y);
        }

        inline Vector2d Vector2l::operator+(Vector2d other)
        {
            return Vector2d((double)this->x + other.x, (double)this->y + other.y);
        }

        inline Vector2l Vector2l::operator+(long value)
        {
            return Vector2l(this->x + value, this->y + value);
        }

        inline Vector2d Vector2l::operator+(double value)
        {
            return Vector2d((double)this->x + value, (double)this->y + value);
        }

        inline double Vector2l::operator*(Vector2l other)
        {
            return this->x * other.x + this->y * other.y;
        }

        inline double Vector2l::operator*(Vector2d other)
        {
            return (double)this->x * other.x + (double)this->y * other.y;
        }

        inline Vector2l Vector2l::operator*(long value)
        {
            return Vector2l(this->x * value, this->y * value);
        }

        inline Vector2d Vector2l::operator*(double value)
        {
            return Vector2d((double)this->x * value, (double)this->y * value);
        }

        inline double Vector2l::sqrLength()
        {
            return this->x * this->x + this->y * this->y;
        }

        inline double Vector2l::lenght()
        {
            return std::sqrt(sqrLength());
        }

        inline void Vector2l::normalize()
        {
            double scale = 1.0 / lenght();
            this->x = (long)((double)this->x * scale);
            this->y = (long)((double)this->y * scale);
        }

        inline Vector2l Vector2l::normalized()
        {
            double scale = 1.0 / lenght();
            return Vector2l((long)((double)this->x * scale), (long)((double)this->y * scale));
        }

        inline double Vector2l::sqrDistance(Vector2l other)
        {
            double dx = (double)this->x - (double)other.x;
            double dy = (double)this->y - (double)other.y;

            return dx * dx + dy * dy;
        }

        inline double Vector2l::distance(Vector2l other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline double Vector2l::sqrDistance(Vector2d other)
        {
            double dx = (double)this->x - other.x;
            double dy = (double)this->y - other.y;

            return dx * dx + dy * dy;
        }

        inline double Vector2l::distance(Vector2d other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline bool Vector2l::equals(Vector2l other)
        {
            return this->x == other.x && this->y == other.y;
        }

        inline bool Vector2l::equals(Vector2d other)
        {
            return (double)this->x == other.x && (double)this->y == other.y;
        }

        inline Vector2l operator*(long value, Vector2l vec)
        {
            return Vector2l(vec.x * value, vec.y * value);
        }

        inline Vector2d operator*(double value, Vector2l vec)
        {
            return Vector2d((double)vec.x * value, (double)vec.y * value);
        }
#pragma endregion

#pragma region VECTOR2D
        Vector2d::Vector2d(double x, double y)
        {
            this->x = x;
            this->y = y;
        }

        Vector2d::Vector2d(double value)
        {
            this->x = value;
            this->y = value;
        }

        Vector2d::Vector2d(Vector2d &other)
        {
            this->x = other.x;
            this->y = other.y;
        }

        Vector2d::Vector2d(Vector2l &other)
        {
            this->x = (double)other.x;
            this->y = (double)other.y;
        }

        Vector2d::Vector2d() {}

        Vector2d::~Vector2d() {}

        inline Vector2d Vector2d::operator+(Vector2d other)
        {
            return Vector2d(this->x + other.x, this->y + other.y);
        }

        inline Vector2d Vector2d::operator+(Vector2l other)
        {
            return Vector2d(this->x + (double)other.x, this->y + (double)other.y);
        }

        inline Vector2d Vector2d::operator+(double value)
        {
            return Vector2d(this->x + value, this->y + value);
        }

        inline Vector2d Vector2d::operator+(long value)
        {
            return Vector2d(this->x + (double)value, this->y + (double)value);
        }

        inline double Vector2d::operator*(Vector2d other)
        {
            return this->x * other.x + this->y * other.y;
        }

        inline double Vector2d::operator*(Vector2l other)
        {
            return this->x * (double)other.x + this->y * (double)other.y;
        }

        inline Vector2d Vector2d::operator*(double value)
        {
            return Vector2d(this->x * value, this->y * value);
        }

        inline Vector2d Vector2d::operator*(long value)
        {
            return Vector2d(this->x * (double)value, this->y * (double)value);
        }

        inline double Vector2d::sqrLength()
        {
            return this->x * this->x + this->y * this->y;
        }

        inline double Vector2d::lenght()
        {
            return std::sqrt(sqrLength());
        }

        inline void Vector2d::normalize()
        {
            double scale = 1.0 / lenght();
            this->x = this->x * scale;
            this->y = this->y * scale;
        }

        inline Vector2d Vector2d::normalized()
        {
            double scale = 1.0 / lenght();
            return Vector2d(this->x * scale, this->y * scale);
        }

        inline double Vector2d::sqrDistance(Vector2d other)
        {
            double dx = this->x - other.x;
            double dy = this->y - other.y;

            return dx * dx + dy * dy;
        }

        inline double Vector2d::distance(Vector2d other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline double Vector2d::sqrDistance(Vector2l other)
        {
            double dx = this->x - (double)other.x;
            double dy = this->y - (double)other.y;

            return dx * dx + dy * dy;
        }

        inline double Vector2d::distance(Vector2l other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline bool Vector2d::equals(Vector2d other)
        {
            return this->x == other.x && this->y == other.y;
        }

        inline bool Vector2d::equals(Vector2l other)
        {
            return this->x == (double)other.x && this->y == (double)other.y;
        }

        inline Vector2d operator*(double value, Vector2d vec)
        {
            return Vector2d(vec.x * value, vec.y * value);
        }

        inline Vector2d operator*(long value, Vector2d vec)
        {
            return Vector2d(vec.x * (double)value, vec.y * (double)value);
        }
#pragma endregion

#pragma region VECTOR3L
        Vector3l::Vector3l(long x, long y, long z)
        {
            this->x = x;
            this->y = y;
            this->z = z;
        }

        Vector3l::Vector3l(long x, long y)
        {
            this->x = x;
            this->y = y;
        }

        Vector3l::Vector3l(long value)
        {
            this->x = value;
            this->y = value;
            this->z = value;
        }

        Vector3l::Vector3l(Vector3l &other)
        {
            this->x = other.x;
            this->y = other.y;
            this->z = other.z;
        }

        Vector3l::Vector3l(Vector3d &other)
        {
            this->x = (long)other.x;
            this->y = (long)other.y;
            this->z = (long)other.z;
        }

        Vector3l::Vector3l() {}

        Vector3l::~Vector3l() {}

        inline Vector3l Vector3l::operator+(Vector3l other)
        {
            return Vector3l(this->x + other.x, this->y + other.y, this->z + other.z);
        }

        inline Vector3d Vector3l::operator+(Vector3d other)
        {
            return Vector3d((double)this->x + other.x, (double)this->y + other.y, (double)this->z + other.z);
        }

        inline Vector3l Vector3l::operator+(long value)
        {
            return Vector3l(this->x + value, this->y + value, this->z + value);
        }

        inline Vector3d Vector3l::operator+(double value)
        {
            return Vector3d((double)this->x + value, (double)this->y + value, (double)this->z + value);
        }

        inline double Vector3l::operator*(Vector3l other)
        {
            return this->x * other.x + this->y * other.y + this->z * other.z;
        }

        inline double Vector3l::operator*(Vector2d other)
        {
            return (double)this->x * other.x + (double)this->y * other.y + (double)this->z * other.z;
        }

        inline Vector3l Vector3l::operator*(long value)
        {
            return Vector3l(this->x * value, this->y * value, this->z * value);
        }

        inline Vector3d Vector3l::operator*(double value)
        {
            return Vector3d((double)this->x * value, (double)this->y * value, (double)this->z * value);
        }

        inline Vector3l Vector3l::operator*(Matrix3x3 mat)
        {
            long nx = mat.x11 * (double)this->x + mat.x12 * (double)this->y + mat.x13 * (double)this->z;
            long ny = mat.x21 * (double)this->x + mat.x22 * (double)this->y + mat.x23 * (double)this->z;
            long nz = mat.x31 * (double)this->x + mat.x32 * (double)this->y + mat.x33 * (double)this->z;

            return Vector3l(nx, ny, nz);
        }

        inline Vector3l Vector3l::X(Vector3l other)
        {
            long nx = this->y * other.z - this->z * other.y;
            long ny = this->z * other.x - this->x * other.z;
            long nz = this->x * other.y - this->y * other.x;

            return Vector3l(nx, ny, nz);
        }

        inline Vector3d Vector3l::X(Vector3d other)
        {
            double nx = (double)this->y * other.z - (double)this->z * other.y;
            double ny = (double)this->z * other.x - (double)this->x * other.z;
            double nz = (double)this->x * other.y - (double)this->y * other.x;

            return Vector3d(nx, ny, nz);
        }

        inline double Vector3l::sqrLength()
        {
            return this->x * this->x + this->y * this->y + this->z * this->z;
        }

        inline double Vector3l::lenght()
        {
            return std::sqrt(sqrLength());
        }

        inline void Vector3l::normalize()
        {
            double scale = 1.0 / lenght();
            this->x = (long)((double)this->x * scale);
            this->y = (long)((double)this->y * scale);
            this->z = (long)((double)this->z * scale);
        }

        inline Vector3l Vector3l::normalized()
        {
            double scale = 1.0 / lenght();
            return Vector3l((long)((double)this->x * scale),
                            (long)((double)this->y * scale),
                            (long)((double)this->z * scale));
        }

        inline double Vector3l::sqrDistance(Vector3l other)
        {
            double dx = (double)this->x - (double)other.x;
            double dy = (double)this->y - (double)other.y;
            double dz = (double)this->z - (double)other.z;

            return dx * dx + dy * dy + dz * dz;
        }

        inline double Vector3l::distance(Vector3l other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline double Vector3l::sqrDistance(Vector3d other)
        {
            double dx = (double)this->x - other.x;
            double dy = (double)this->y - other.y;
            double dz = (double)this->z - other.z;

            return dx * dx + dy * dy + dz * dz;
        }

        inline double Vector3l::distance(Vector3d other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline Vector3l Vector3l::reflected(Vector3l other)
        {
            return *this - 2.0 * (other * (*this)) * other;
        }

        inline Vector3d Vector3l::reflected(Vector3d other)
        {
            return *this - 2.0 * (other * (*this)) * other;
        }

        inline bool Vector3l::equals(Vector3l other)
        {
            return this->x == other.x && this->y == other.y && this->z == other.z;
        }

        inline bool Vector3l::equals(Vector3d other)
        {
            return (double)this->x == other.x && (double)this->y == other.y && (double)this->z == other.z;
        }

        inline Vector3l operator*(long value, Vector3l vec)
        {
            return Vector3l(vec.x * value, vec.y * value, vec.z * value);
        }

        inline Vector3d operator*(double value, Vector3l vec)
        {
            return Vector3d((double)vec.x * value, (double)vec.y * value, (double)vec.z * value);
        }
#pragma endregion

#pragma region VECTOR3D
        Vector3d::Vector3d(double x, double y, double z)
        {
            this->x = x;
            this->y = y;
            this->z = z;
        }

        Vector3d::Vector3d(double x, double y)
        {
            this->x = x;
            this->y = y;
        }

        Vector3d::Vector3d(double value)
        {
            this->x = value;
            this->y = value;
            this->z = value;
        }

        Vector3d::Vector3d(Vector3d &other)
        {
            this->x = other.x;
            this->y = other.y;
            this->z = other.z;
        }

        Vector3d::Vector3d(Vector3l &other)
        {
            this->x = (double)other.x;
            this->y = (double)other.y;
            this->z = (double)other.z;
        }

        Vector3d::Vector3d() {}

        Vector3d::~Vector3d() {}

        inline Vector3d Vector3d::operator+(Vector3d other)
        {
            return Vector3d(this->x + other.x, this->y + other.y, this->z + other.z);
        }

        inline Vector3d Vector3d::operator+(Vector3l other)
        {
            return Vector3d(this->x + (double)other.x, this->y + (double)other.y, this->z + (double)other.z);
        }

        inline Vector3d Vector3d::operator+(double value)
        {
            return Vector3d(this->x + value, this->y + value, this->z + value);
        }

        inline Vector3d Vector3d::operator+(long value)
        {
            return Vector3d(this->x + (double)value, this->y + (double)value, this->z + (double)value);
        }

        inline double Vector3d::operator*(Vector3d other)
        {
            return this->x * other.x + this->y * other.y + this->z * other.z;
        }

        inline double Vector3d::operator*(Vector3l other)
        {
            return this->x * (double)other.x + this->y * (double)other.y + this->z * (double)other.z;
        }

        inline Vector3d Vector3d::operator*(double value)
        {
            return Vector3d(this->x * value, this->y * value, this->z * value);
        }

        inline Vector3d Vector3d::operator*(long value)
        {
            return Vector3d(this->x * (double)value, this->y * (double)value, this->z * (double)value);
        }

        inline Vector3d Vector3d::operator*(Matrix3x3 mat)
        {
            double nx = mat.x11 * this->x + mat.x12 * this->y + mat.x13 * this->z;
            double ny = mat.x21 * this->x + mat.x22 * this->y + mat.x23 * this->z;
            double nz = mat.x31 * this->x + mat.x32 * this->y + mat.x33 * this->z;

            return Vector3d(nx, ny, nz);
        }

        inline Vector3d Vector3d::X(Vector3d other)
        {
            double nx = this->y * other.z - this->z * other.y;
            double ny = this->z * other.x - this->x * other.z;
            double nz = this->x * other.y - this->y * other.x;

            return Vector3d(nx, ny, nz);
        }

        inline Vector3d Vector3d::X(Vector3l other)
        {
            double nx = this->y * (double)other.z - this->z * (double)other.y;
            double ny = this->z * (double)other.x - this->x * (double)other.z;
            double nz = this->x * (double)other.y - this->y * (double)other.x;

            return Vector3d(nx, ny, nz);
        }

        inline double Vector3d::sqrLength()
        {
            return this->x * this->x + this->y * this->y + this->z * this->z;
        }

        inline double Vector3d::lenght()
        {
            return std::sqrt(sqrLength());
        }

        inline void Vector3d::normalize()
        {
            double scale = 1.0 / lenght();
            this->x = this->x * scale;
            this->y = this->y * scale;
            this->z = this->z * scale;
        }

        inline Vector3d Vector3d::normalized()
        {
            double scale = 1.0 / lenght();
            return Vector3d(this->x * scale, this->y * scale, this->z * scale);
        }

        inline double Vector3d::sqrDistance(Vector3d other)
        {
            double dx = this->x - other.x;
            double dy = this->y - other.y;
            double dz = this->z - other.z;

            return dx * dx + dy * dy + dz * dz;
        }

        inline double Vector3d::distance(Vector3d other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline double Vector3d::sqrDistance(Vector3l other)
        {
            double dx = this->x - (double)other.x;
            double dy = this->y - (double)other.y;
            double dz = this->z - (double)other.z;

            return dx * dx + dy * dy + dz * dz;
        }

        inline double Vector3d::distance(Vector3l other)
        {
            return std::sqrt(sqrDistance(other));
        }

        inline Vector3d Vector3d::reflected(Vector3d other)
        {
            return *this - 2.0 * (other * (*this)) * other;
        }

        inline Vector3d Vector3d::reflected(Vector3l other)
        {
            return *this - 2.0 * (other * (*this)) * other;
        }

        inline bool Vector3d::equals(Vector3d other)
        {
            return this->x == other.x && this->y == other.y && this->z == other.z;
        }

        inline bool Vector3d::equals(Vector3l other)
        {
            return this->x == (double)other.x && this->y == (double)other.y && this->z == (double)other.z;
        }

        inline Vector3d operator*(double value, Vector3d vec)
        {
            return Vector3d(vec.x * value, vec.y * value, vec.z * value);
        }

        inline Vector3d operator*(long value, Vector3d vec)
        {
            return Vector3d(vec.x * (double)value, vec.y * (double)value, vec.z * (double)value);
        }
#pragma endregion
    } // namespace math
} // namespace rc
