package top.ilovestudy.learn.link;

import lombok.Data;

/**
 * @author jinghui.zhang
 */
@Data
public class SNode<T> {
  T element;
  SNode<T> next;

  public SNode(T element) {
    this.element = element;
  }
}
