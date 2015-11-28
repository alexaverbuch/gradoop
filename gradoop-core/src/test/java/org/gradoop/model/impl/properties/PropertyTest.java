package org.gradoop.model.impl.properties;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.gradoop.model.api.EPGMProperty;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import static org.junit.Assert.*;

public class PropertyTest {

  @Test
  public void testGetKey() throws Exception {
    EPGMProperty property = new Property("key", PropertyValue.create(10));
    assertEquals("key", property.getKey());
  }

  @Test
  public void testSetKey() throws Exception {
    EPGMProperty property = new Property("key", PropertyValue.create(10));
    property.setKey("newKey");
    assertEquals("newKey", property.getKey());
  }

  @Test(expected = NullPointerException.class)
  public void testSetKeyNull() {
    new Property(null, PropertyValue.create(10));
  }

  @Test(expected = NullPointerException.class)
  public void testSetKeyNull2() {
    EPGMProperty property = new Property("key", PropertyValue.create(10));
    property.setKey(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetKeyEmpty() {
    new Property("", PropertyValue.create(10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetKeyEmpty2() {
    EPGMProperty property = new Property("key", PropertyValue.create(10));
    property.setKey("");
  }

  @Test
  public void testGetValue() throws Exception {
    PropertyValue propertyValue = PropertyValue.create(10);
    EPGMProperty p = new Property("key", propertyValue);
    assertEquals(propertyValue, p.getValue());
  }

  @Test
  public void testSetValue() throws Exception {
    PropertyValue propertyValue = PropertyValue.create(10);
    EPGMProperty p = new Property("key", PropertyValue.create(11));
    p.setValue(propertyValue);
    assertEquals(propertyValue, p.getValue());
  }

  @Test (expected = NullPointerException.class)
  public void testSetValueNull() {
    EPGMProperty p = new Property("key", PropertyValue.create(11));
    p.setValue(null);
  }

  @Test (expected = NullPointerException.class)
  public void testSetValueNull2() {
    new Property("key", null);
  }

  @Test
  public void testEqualsAndHashCode() throws Exception {
    Property p1 = new Property("key1", PropertyValue.create(10));
    Property p2 = new Property("key1", PropertyValue.create(10));
    Property p3 = new Property("key1", PropertyValue.create(11));
    Property p4 = new Property("key2", PropertyValue.create(10));
    Property p5 = new Property("key2", PropertyValue.create(11));

    assertEquals(p1, p1);
    assertEquals(p1, p2);
    assertNotEquals(p1, p3);
    assertNotEquals(p1, p4);
    assertNotEquals(p1, p5);

    assertTrue(p1.hashCode() == p1.hashCode());
    assertTrue(p1.hashCode() == p2.hashCode());
    assertFalse(p1.hashCode() == p3.hashCode());
    assertFalse(p1.hashCode() == p4.hashCode());
    assertFalse(p1.hashCode() == p5.hashCode());
  }

  @Test
  public void testCompareTo() throws Exception {
    Property p1 = new Property("key1", PropertyValue.create(10));
    Property p2 = new Property("key1", PropertyValue.create(10));
    Property p3 = new Property("key2", PropertyValue.create(10));

    assertEquals(0, p1.compareTo(p1));
    assertEquals(0, p1.compareTo(p2));
    assertEquals(-1, p1.compareTo(p3));
    assertEquals(1, p3.compareTo(p1));
  }

  @Test
  public void testWriteAndReadFields() throws Exception {
    Property p1 = new Property("key", PropertyValue.create(10));

    // write to byte[]
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DataOutputStream dataOut = new DataOutputStream(out);
    p1.write(dataOut);

    // read from byte[]
    Property p2 = new Property();
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    DataInputStream dataIn = new DataInputStream(in);
    p2.readFields(dataIn);

    assertEquals(p1, p2);
  }
}