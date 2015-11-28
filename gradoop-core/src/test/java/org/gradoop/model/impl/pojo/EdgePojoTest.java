package org.gradoop.model.impl.pojo;

import org.gradoop.model.api.EPGMEdge;
import org.gradoop.model.impl.id.GradoopId;
import org.gradoop.model.impl.id.GradoopIdSet;
import org.gradoop.model.api.EPGMProperties;
import org.gradoop.model.impl.properties.Properties;
import org.gradoop.util.GConstants;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EdgePojoTest {

  @Test
  public void createWithIDTest() {
    GradoopId edgeId = GradoopId.get();
    GradoopId sourceId = GradoopId.get();
    GradoopId targetId = GradoopId.get();
    EPGMEdge e =
      new EdgePojoFactory().initEdge(edgeId, sourceId, targetId);
    assertThat(e.getId(), is(edgeId));
    assertThat(e.getSourceId(), is(sourceId));
    assertThat(e.getTargetId(), is(targetId));
    assertThat(e.getPropertyCount(), is(0));
    assertThat(e.getGraphCount(), is(0));
  }

  @Test
  public void createEdgePojoTest() {
    GradoopId edgeId = GradoopId.get();
    GradoopId sourceId = GradoopId.get();
    GradoopId targetId = GradoopId.get();
    GradoopIdSet graphIds = GradoopIdSet
      .fromExisting(GradoopId.get(), GradoopId.get());

    String label = "A";
    EPGMProperties props = new Properties();
    props.set("k1", "v1");
    props.set("k2", "v2");

    EPGMEdge edge = new EdgePojoFactory()
      .initEdge(edgeId, label, sourceId, targetId, props, graphIds);

    assertThat(edge.getId(), is(edgeId));
    assertEquals(label, edge.getLabel());
    assertThat(edge.getSourceId(), is(sourceId));
    assertThat(edge.getTargetId(), is(targetId));
    assertThat(edge.getPropertyCount(), is(2));
    assertThat(edge.getPropertyValue("k1").getString(), Is.<Object>is("v1"));
    assertThat(edge.getPropertyValue("k2").getString(), Is.<Object>is("v2"));
    assertThat(edge.getGraphCount(), is(2));

    for(GradoopId graphId : graphIds) {
      assertTrue(edge.getGraphIds().contains(graphId));
    }
  }

  @Test
  public void createWithMissingLabelTest() {
    GradoopId edgeId = GradoopId.get();
    GradoopId sourceId = GradoopId.get();
    GradoopId targetId = GradoopId.get();
    EPGMEdge e =
      new EdgePojoFactory().initEdge(edgeId, sourceId, targetId);
    assertThat(e.getLabel(), is(GConstants.DEFAULT_EDGE_LABEL));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullIDTest() {
    GradoopId sourceId = GradoopId.get();
    GradoopId targetId = GradoopId.get();
    new EdgePojoFactory().initEdge(null, sourceId, targetId);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullSourceIdTest() {
    GradoopId edgeId = GradoopId.get();
    GradoopId targetId = GradoopId.get();
    new EdgePojoFactory().initEdge(edgeId, null, targetId);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullTargetIdTest() {
    GradoopId edgeId = GradoopId.get();
    GradoopId sourceId = GradoopId.get();
    new EdgePojoFactory().initEdge(edgeId, sourceId, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createWithNullLabelTest() {
    GradoopId edgeId = GradoopId.get();
    GradoopId sourceId = GradoopId.get();
    GradoopId targetId = GradoopId.get();
    new EdgePojoFactory().initEdge(edgeId, null, sourceId, targetId);
  }
}