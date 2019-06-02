package ru.avalon.java.j30.labs;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * Класс описывает представление о коде товара и отражает соответствующую
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {
 
    /**
     * Код товара
     */
    private static String code;
    /**
     * Кода скидки
     */
    private static char discountCode;
    /**
     * Описание
     */
    private static String description;
 
    /**
     * Основной конструктор типа {@link ProductCode}
     *
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание
     */
    public ProductCode(String code, char discountCode, String description) {
        ProductCode.code = code;
        ProductCode.discountCode = discountCode;
        ProductCode.description = description;
    }
 
    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) {
        /*
        * TODO #05 реализуйте конструктор класса ProductCode
         */
        try {
            ProductCode.code = set.getString("PROD_CODE");
            ProductCode.discountCode = set.getString("DISCOUNT_CODE").charAt(0);
            ProductCode.description = set.getString("DESCRIPTION");
        } catch (SQLException ex) {
            Logger.getLogger(ProductCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /**
     * Возвращает код товара
     *
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }
 
    /**
     * Устанавливает код товара
     *
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }
 
    /**
     * Возвращает код скидки
     *
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }
 
    /**
     * Устанавливает код скидки
     *
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
 
    /**
     * Возвращает описание
     *
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }
 
    /**
     * Устанавливает описание
     *
     * @param description описание
     */
    public void setDescription(String description) {
        ProductCode.description = description;
    }
 
    /**
     * Хеш-функция типа {@link ProductCode}.
     *
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (int) discountCode;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
 
    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа
     * {@link ProductCode}
     *
     * @param o Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном
     * случае - false.
     */
    @Override
    public boolean equals(Object o) {
        /*
         * TODO #07 Реализуйте метод equals
         */
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
 
        ProductCode that = (ProductCode) o;
 
        if (discountCode != that.discountCode) {
            return false;
        }
        if (code != null ? !code.equals(that.code) : that.code != null) {
            return false;
        }
        return description != null ? description.equals(that.description) : that.description == null;
    }
 
    /**
     * Возвращает строковое представление кода товара.
     *
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        /*
         * TODO #08 Реализуйте метод toString
         */
        return "ProductCode{"
                + "code='" + code + '\''
                + ", discountCode=" + discountCode
                + ", description='" + description + '\''
                + '}';
    }
 
    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE базы
     * данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException {
        /*
        * TODO #09 Реализуйте метод getSelectQuery
         */
        return connection.prepareStatement("SELECT * FROM PRODUCT_CODE");
    }
 
    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE базы данных
     * Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        String insertSQL = "INSERT INTO PRODUCT_CODE (PROD_CODE, DISCOUNT_CODE, DESCRIPTION) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
        preparedStatement.setString(1, ProductCode.code);
        preparedStatement.setString(2, String.valueOf(ProductCode.discountCode));
        preparedStatement.setString(3, ProductCode.description);
 
        return preparedStatement;
    }
 
    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE
     * базы данных Sample
     *
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
         */
        //String insertSQL = "update PRODUCT_CODE set (PROD_CODE, DISCOUNT_CODE, DESCRIPTION) VALUES (?, ?, ?) where PROD_CODE = ?";
 
        String updateTableSQL = "UPDATE PRODUCT_CODE SET PROD_CODE = ?, DISCOUNT_CODE = ?, DESCRIPTION = ?"
                + " WHERE PROD_CODE = ?";
 
        PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
        preparedStatement.setString(1, ProductCode.code);
        preparedStatement.setString(2, String.valueOf(ProductCode.discountCode));
        preparedStatement.setString(3, ProductCode.description);
        preparedStatement.setString(4, ProductCode.code);
 
        return preparedStatement;
    }
 
    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа
     * {@link ProductCode}
     *
     * @param set {@link ResultSet}, полученный в результате запроса,
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */
        List<ProductCode> data = new ArrayList<>();
        while (set.next()) {
            ProductCode productCode = new ProductCode(set.getString(1), set.getString(2).charAt(0), set.getString(3));
            data.add(productCode);
        }
 
        return data;
    }
 
    /**
     * Сохраняет текущий объект в базе данных.
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа
     * UPDATE.
     *
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        /*
         * TODO #13 Реализуйте метод convert
         */
        boolean isExisted = false;
        Collection<ProductCode> productCodeCollection = all(connection);
        for (ProductCode productCode : productCodeCollection) {
            if (productCode.code.equals(this.code)) {
                isExisted = true;
                break;
            }
        }
 
        if (isExisted) {
            getUpdateQuery(connection).execute();
        } else {
            getInsertQuery(connection).execute();
        }
 
    }
 
    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов типа
     * {@link ProductCode}
     *
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }
}