package org.gradoop.model.impl.operators.summarization.tuples;

import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Representation of a vertex id and its corresponding vertex group
 * representative.
 *
 * Consists of:
 *
 * <ul>
 * <li> vertex id
 * <li> group representative vertex id
 * </ul>
 */
public class VertexWithRepresentative extends Tuple2<Long, Long> {

  public Long getVertexId() {
    return f0;
  }

  public void setVertexId(Long vertexId) {
    f0 = vertexId;
  }

  public Long getGroupRepresentativeVertexId() {
    return f1;
  }

  public void setGroupRepresentativeVertexId(Long groupRepresentativeVertexId) {
    f1 = groupRepresentativeVertexId;
  }
}