/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc;

/**
 *
 * @author Абс0лютный Н0ль
 *
 * Класс цвета в пространстве RGB с alpha-каналом
 */
public class Color {

    private double a, r, g, b;

    /**
     * Создаёт экземпляр класса с прозрачностью - a, коэффициентами красного -
     * r, зелёного - g, синего - b
     */
    public Color(double a, double r, double g, double b) {
        this.a = a < 0.0 ? 0.0 : (a > 1.0 ? 1.0 : a);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Создаёт экземпляр класса с коэффициентами красного - r, зелёного - g,
     * синего - b
     */
    public Color(double r, double g, double b) {
        this(1, r, g, b);
    }

    /**
     * Значение alpha-канала
     */
    public double getAlpha() {
        return this.a;
    }

    public void setAlpha(double alpha) {
        this.a = alpha;
    }

    /**
     * Значение коэффициента красного
     */
    public double getRed() {
        return this.r;
    }

    public void setRed(double red) {
        this.r = red;
    }

    /**
     * Значение коэффициента зелёного
     */
    public double getGreen() {
        return this.g;
    }

    public void setGreen(double green) {
        this.g = green;
    }

    /**
     * Значение коэффициента синего
     */
    public double getBlue() {
        return this.b;
    }

    public void setBlue(double blue) {
        this.b = blue;
    }

    /**
     * Returns the RGB value representing the color in the default sRGB
     * {@link ColorModel}. (Bits 24-31 are alpha, 16-23 are red, 8-15 are green,
     * 0-7 are blue).
     *
     * @return the RGB value of the color in the default sRGB
     * {@code ColorModel}.
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @since 1.0
     */
    public int getRGB() {
        this.r *= this.a;
        this.r = this.r < 0.0 ? 0.0 : (this.r > 1.0 ? 1.0 : this.r);
        int R = (int) (this.r * 255 + 0.5);

        this.g *= this.a;
        this.g = this.g < 0.0 ? 0.0 : (this.g > 1.0 ? 1.0 : this.g);
        int G = (int) (this.g * 255 + 0.5);

        this.b *= this.a;
        this.b = this.b < 0.0 ? 0.0 : (this.b > 1.0 ? 1.0 : this.b);
        int B = (int) (this.b * 255 + 0.5);

        int result = ((255 & 0xFF) << 24)
                | ((R & 0xFF) << 16)
                | ((G & 0xFF) << 8)
                | ((B & 0xFF) << 0);

        return result;
    }

    /**
     * Сложение цветов
     */
    public Color add(Color other) {
        double na = (this.a + other.a) * 0.5;
        double nr = this.r + other.r;
        double ng = this.g + other.g;
        double nb = this.b + other.b;

        return new Color(na, nr, ng, nb);
    }

    /**
     * Вычитание цветов
     */
    public Color sub(Color other) {
        double na = (this.a + other.a) * 0.5;
        double nr = this.r - other.r;
        double ng = this.g - other.g;
        double nb = this.b - other.b;

        return new Color(na, nr, ng, nb);
    }

    /**
     * Преумножение цвета на коэффициент value
     */
    public Color product(double value) {
        double nr = this.r * value;
        double ng = this.g * value;
        double nb = this.b * value;

        return new Color(this.a, nr, ng, nb);
    }

    /**
     * Альфа-смешивание цветов
     */
    public Color alphaBlend(Color other) {
        double na = other.a + this.a * (1.0 - other.a);
        if (na == 0.0) {
            return new Color(0, 0, 0, 0);
        }

        double scale = 1.0 / na;
        double nr = (other.r * other.a + this.r * this.a * (1.0 - other.a)) * scale;
        double ng = (other.g * other.a + this.g * this.a * (1.0 - other.a)) * scale;
        double nb = (other.b * other.a + this.b * this.a * (1.0 - other.a)) * scale;

        return new Color(na, nr, ng, nb);
    }

    /**
     * Альфа-смешивание цветов с заданным значение alpha-канала - alpha
     */
    public Color alphaBlend(Color other, double alpha) {
        return alphaBlend(new Color(alpha, other.r, other.g, other.b));
    }

    /**
     * Предварительно загашенный alpha-каналом цвет Возвращает новый цвет с
     * alpha-каналом = 1 и каждым коэффициентом умноженным на старое значение
     * alpha-канала
     */
    public Color premulti() {
        return new Color(this.a * this.r, this.a * this.g, this.a * this.b);
    }

    /**
     *
     * @param other
     * @return
     */
    public Color blend(Color other) {
        return new Color(0.5 * (this.a + other.a),
                0.5 * (this.r + other.r),
                0.5 * (this.g + other.g),
                0.5 * (this.b + other.b));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Color color = (Color) obj;
        return this.a == color.a && this.r == color.r && this.g == color.g && this.b == color.b;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.a) ^ (Double.doubleToLongBits(this.a) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.r) ^ (Double.doubleToLongBits(this.r) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.g) ^ (Double.doubleToLongBits(this.g) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.b) ^ (Double.doubleToLongBits(this.b) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.format("(a: %f, r: %f, g: %f, b: %f)", this.a, this.r, this.g, this.b);
    }

    public static Color black() {
        return new Color(0, 0, 0);
    }

    public static Color white() {
        return new Color(1, 1, 1);
    }

    public static Color red() {
        return new Color(1, 0, 0);
    }

    public static Color green() {
        return new Color(0, 1, 0);
    }

    public static Color blue() {
        return new Color(0, 0, 1);
    }

    public static Color lightGrey() {
        return new Color(0.75, 0.75, 0.75);
    }

    public static Color grey() {
        return new Color(0.5, 0.5, 0.5);
    }

    public static Color darkGrey() {
        return new Color(0.25, 0.25, 0.25);
    }

    public static Color transparent() {
        return new Color(0, 0, 0, 0);
    }
}
