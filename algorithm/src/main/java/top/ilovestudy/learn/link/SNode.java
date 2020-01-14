package top.ilovestudy.learn.link;

import lombok.Data;

/**
 * @author jinghui.zhang
 */
@Data
public class SNode<T> {
  T value;
  SNode<T> next;

  public SNode(T node) {
    this.value = node;
  }
}
