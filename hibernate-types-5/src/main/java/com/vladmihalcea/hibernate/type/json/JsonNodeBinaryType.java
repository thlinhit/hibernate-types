package com.vladmihalcea.hibernate.type.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.internal.JsonBinarySqlTypeDescriptor;
import com.vladmihalcea.hibernate.type.json.internal.JsonNodeTypeDescriptor;
import com.vladmihalcea.hibernate.type.util.ObjectMapperWrapper;
import com.vladmihalcea.hibernate.type.util.Configuration;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;

/**
 * Maps a Jackson {@link JsonNode} object on a JSON column type that is managed via {@link java.sql.PreparedStatement#setObject(int, Object)} at JDBC Driver level. For instance, if you are using PostgreSQL, you should be using {@link JsonNodeBinaryType} to map both {@code jsonb} and {@code json} column types to a Jackson {@link JsonNode} object.
 *
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/how-to-store-schema-less-eav-entity-attribute-value-data-using-json-and-hibernate/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @author Vlad Mihalcea
 */
public class JsonNodeBinaryType
        extends AbstractSingleColumnStandardBasicType<JsonNode> {

    public static final JsonNodeBinaryType INSTANCE = new JsonNodeBinaryType();

    public JsonNodeBinaryType() {
        super(
                JsonBinarySqlTypeDescriptor.INSTANCE,
                new JsonNodeTypeDescriptor(Configuration.INSTANCE.getObjectMapperWrapper())
        );
    }

    public JsonNodeBinaryType(ObjectMapper objectMapper) {
        super(
                JsonBinarySqlTypeDescriptor.INSTANCE,
                new JsonNodeTypeDescriptor(new ObjectMapperWrapper(objectMapper))
        );
    }

    public JsonNodeBinaryType(ObjectMapperWrapper objectMapperWrapper) {
        super(
                JsonBinarySqlTypeDescriptor.INSTANCE,
                new JsonNodeTypeDescriptor(objectMapperWrapper)
        );
    }

    public String getName() {
        return "jsonb-node";
    }
}