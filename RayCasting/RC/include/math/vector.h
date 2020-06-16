#ifndef VECTOR2_H
#define VECTOR2_H

namespace rc
{
    namespace math
    {
#include <cmath>
#include <ostream>
#include "matrix.h"

        class Vector2l;
        class Vector2d;
        class Vector3l;
        class Vector3d;

#pragma region VECTOR2L
        class Vector2l
        {
        public:
            Vector2l(long x, long y);

            Vector2l(long value);

            Vector2l(Vector2l &other);

            Vector2l(Vector2d &other);

            Vector2l();

            ~Vector2l();

            long x = 0, y = 0;

            inline Vector2l operator+(Vector2l other);

            inline Vector2d operator+(Vector2d other);

            inline Vector2l operator+(long value);

            inline Vector2d operator+(double value);

            inline double operator*(Vector2l other);

            inline double operator*(Vector2d other);

            inline Vector2l operator*(long value);

            inline Vector2d operator*(double value);

            inline double sqrLength();

            inline double lenght();

            inline void normalize();

            inline Vector2l normalized();

            inline double sqrDistance(Vector2l other);

            inline double distance(Vector2l other);

            inline double sqrDistance(Vector2d other);

            inline double distance(Vector2d other);

            inline bool equals(Vector2l other);

            inline bool equals(Vector2d other);

            friend std::ostream &operator<<(std::ostream &out, const Vector2l &vec)
            {
                out << "(" << vec.x << "; " << vec.y << ")";
                return out;
            }
        };

        inline Vector2l operator*(long value, Vector2l vec);

        inline Vector2d operator*(double value, Vector2l vec);
#pragma endregion

#pragma region VECTOR2D
        class Vector2d
        {
        public:
            Vector2d(double x, double y);

            Vector2d(double value);

            Vector2d(Vector2d &other);

            Vector2d(Vector2l &other);

            Vector2d();

            ~Vector2d();

            double x = 0.0, y = 0.0;

            inline Vector2d operator+(Vector2d other);

            inline Vector2d operator+(Vector2l other);

            inline Vector2d operator+(double value);

            inline Vector2d operator+(long value);

            inline double operator*(Vector2d other);

            inline double operator*(Vector2l other);

            inline Vector2d operator*(double value);

            inline Vector2d operator*(long value);

            inline double sqrLength();

            inline double lenght();

            inline void normalize();

            inline Vector2d normalized();

            inline double sqrDistance(Vector2d other);

            inline double distance(Vector2d other);

            inline double sqrDistance(Vector2l other);

            inline double distance(Vector2l other);

            inline bool equals(Vector2d other);

            inline bool equals(Vector2l other);

            friend std::ostream &operator<<(std::ostream &out, const Vector2d &vec)
            {
                out << "(" << vec.x << "; " << vec.y << ")";
                return out;
            }
        };

        inline Vector2d operator*(double value, Vector2d vec);

        inline Vector2d operator*(long value, Vector2d vec);
#pragma endregion

#pragma region VECTOR3L
        class Vector3l
        {
        public:
            Vector3l(long x, long y, long z);

            Vector3l(long x, long y);

            Vector3l(long value);

            Vector3l(Vector3l &other);

            Vector3l(Vector3d &other);

            Vector3l();

            ~Vector3l();

            long x = 0, y = 0, z = 0;

            inline Vector3l operator+(Vector3l other);

            inline Vector3d operator+(Vector3d other);

            inline Vector3l operator+(long value);

            inline Vector3d operator+(double value);

            inline double operator*(Vector3l other);

            inline double operator*(Vector2d other);

            inline Vector3l operator*(long value);

            inline Vector3d operator*(double value);

            inline Vector3l operator*(Matrix3x3 mat);

            inline Vector3l X(Vector3l other);

            inline Vector3d X(Vector3d other);

            inline double sqrLength();

            inline double lenght();

            inline void normalize();

            inline Vector3l normalized();

            inline double sqrDistance(Vector3l other);

            inline double distance(Vector3l other);

            inline double sqrDistance(Vector3d other);

            inline double distance(Vector3d other);

            inline Vector3l reflected(Vector3l other);

            inline Vector3d reflected(Vector3d other);

            inline bool equals(Vector3l other);

            inline bool equals(Vector3d other);

            friend std::ostream &operator<<(std::ostream &out, const Vector3l &vec)
            {
                out << "(" << vec.x << "; " << vec.y << "; " << vec.z << ")";
                return out;
            }
        };

        inline Vector3l operator*(long value, Vector3l vec);

        inline Vector3d operator*(double value, Vector3l vec);
#pragma endregion

#pragma region VECTOR3D
        class Vector3d
        {
        public:
            Vector3d(double x, double y, double z);

            Vector3d(double x, double y);

            Vector3d(double value);

            Vector3d(Vector3d &other);

            Vector3d(Vector3l &other);

            Vector3d();

            ~Vector3d();

            double x = 0.0, y = 0.0, z = 0.0;

            inline Vector3d operator+(Vector3d other);

            inline Vector3d operator+(Vector3l other);

            inline Vector3d operator+(double value);

            inline Vector3d operator+(long value);

            inline double operator*(Vector3d other);

            inline double operator*(Vector3l other);

            inline Vector3d operator*(double value);

            inline Vector3d operator*(long value);

            inline Vector3d operator*(Matrix3x3 mat);

            inline Vector3d X(Vector3d other);

            inline Vector3d X(Vector3l other);

            inline double sqrLength();

            inline double lenght();

            inline void normalize();

            inline Vector3d normalized();

            inline double sqrDistance(Vector3d other);

            inline double distance(Vector3d other);

            inline double sqrDistance(Vector3l other);

            inline double distance(Vector3l other);

            inline Vector3d reflected(Vector3d other);

            inline Vector3d reflected(Vector3l other);

            inline bool equals(Vector3d other);

            inline bool equals(Vector3l other);

            friend std::ostream &operator<<(std::ostream &out, const Vector3d &vec)
            {
                out << "(" << vec.x << "; " << vec.y << "; " << vec.z << ")";
                return out;
            }
        };

        inline Vector3d operator*(double value, Vector3d vec);

        inline Vector3d operator*(long value, Vector3d vec);
#pragma endregion
    } // namespace math
} // namespace rc

#endif // VECTOR2_H
