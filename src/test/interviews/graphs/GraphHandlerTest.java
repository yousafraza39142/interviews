package interviews.graphs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class.
 * @author Francois Rousseau
 */
public class GraphHandlerTest extends GraphTest {
  protected GraphHandler<Integer> gh;

  @Override
  protected void setUpDirected() {
    super.setUpDirected();
    gh = new GraphHandler<Integer>(g);
  }

  @Override
  protected void setUpUndirected() {
    super.setUpUndirected();
    gh = new GraphHandler<Integer>(g);
  }

  @Test
  public void test_bfs() {
    setUpDirected();
    gh.bfs(0);
    Assert.assertEquals(null, gh.parent(0));
    Assert.assertEquals(new Integer(0), gh.parent(1));
    Assert.assertEquals(new Integer(4), gh.parent(2));
    Assert.assertEquals(new Integer(4), gh.parent(3));
    Assert.assertEquals(new Integer(5), gh.parent(4));
    Assert.assertEquals(new Integer(0), gh.parent(5));
    Assert.assertEquals(null, gh.parent(6));
    Assert.assertEquals(null, gh.parent(7));
    Assert.assertEquals(null, gh.parent(8));
    Assert.assertEquals(null, gh.parent(9));
    Assert.assertEquals(null, gh.parent(10));
    Assert.assertEquals(null, gh.parent(11));
    Assert.assertEquals(null, gh.parent(12));
    Assert.assertEquals("[3, 4, 5, 0]", gh.pathTo(3).toString());

    setUpUndirected();
    gh.bfs(0);
    Assert.assertEquals(null, gh.parent(0));
    Assert.assertEquals(new Integer(0), gh.parent(1));
    Assert.assertEquals(new Integer(0), gh.parent(2));
    Assert.assertEquals(new Integer(5), gh.parent(3));
    Assert.assertEquals(new Integer(6), gh.parent(4));
    Assert.assertEquals(new Integer(0), gh.parent(5));
    Assert.assertEquals(new Integer(0), gh.parent(6));
    Assert.assertEquals(null, gh.parent(7));
    Assert.assertEquals(null, gh.parent(8));
    Assert.assertEquals(null, gh.parent(9));
    Assert.assertEquals(null, gh.parent(10));
    Assert.assertEquals(null, gh.parent(11));
    Assert.assertEquals(null, gh.parent(12));
    Assert.assertEquals("[3, 5, 0]", gh.pathTo(3).toString());
    Assert.assertEquals("[4, 6, 0]", gh.pathTo(4).toString());
  }

  @Test
  public void test_cc() {
    setUpUndirected();
    gh.cc();
    Assert.assertEquals(3, gh.count());
    Assert.assertEquals(0, gh.id(0));
    Assert.assertEquals(0, gh.id(1));
    Assert.assertEquals(0, gh.id(2));
    Assert.assertEquals(0, gh.id(3));
    Assert.assertEquals(0, gh.id(4));
    Assert.assertEquals(0, gh.id(5));
    Assert.assertEquals(0, gh.id(6));
    Assert.assertEquals(1, gh.id(7));
    Assert.assertEquals(1, gh.id(8));
    Assert.assertEquals(2, gh.id(9));
    Assert.assertEquals(2, gh.id(10));
    Assert.assertEquals(2, gh.id(11));
    Assert.assertEquals(2, gh.id(12));

    Assert.assertEquals(null, gh.parent(0));
    Assert.assertEquals(new Integer(0), gh.parent(1));
    Assert.assertEquals(new Integer(0), gh.parent(2));
    Assert.assertEquals(new Integer(5), gh.parent(3));
    Assert.assertEquals(new Integer(3), gh.parent(4));
    Assert.assertEquals(new Integer(0), gh.parent(5));
    Assert.assertEquals(new Integer(4), gh.parent(6));
    Assert.assertEquals(null, gh.parent(7));
    Assert.assertEquals(new Integer(7), gh.parent(8));
    Assert.assertEquals(null, gh.parent(9));
    Assert.assertEquals(new Integer(9), gh.parent(10));
    Assert.assertEquals(new Integer(9), gh.parent(11));
    Assert.assertEquals(new Integer(11), gh.parent(12));
  }

  @Test
  public void test_dfs() {
    setUpDirected();
    gh.dfs(0);
    Assert.assertEquals(null, gh.parent(0));
    Assert.assertEquals(new Integer(0), gh.parent(1));
    Assert.assertEquals(new Integer(3), gh.parent(2));
    Assert.assertEquals(new Integer(4), gh.parent(3));
    Assert.assertEquals(new Integer(5), gh.parent(4));
    Assert.assertEquals(new Integer(0), gh.parent(5));
    Assert.assertEquals(null, gh.parent(6));
    Assert.assertEquals(null, gh.parent(7));
    Assert.assertEquals(null, gh.parent(8));
    Assert.assertEquals(null, gh.parent(9));
    Assert.assertEquals(null, gh.parent(10));
    Assert.assertEquals(null, gh.parent(11));
    Assert.assertEquals(null, gh.parent(12));
    Assert.assertEquals("[2, 3, 4, 5, 0]", gh.pathTo(2).toString());

    setUpUndirected();
    gh.dfs(0);
    Assert.assertEquals(null, gh.parent(0));
    Assert.assertEquals(new Integer(0), gh.parent(1));
    Assert.assertEquals(new Integer(0), gh.parent(2));
    Assert.assertEquals(new Integer(5), gh.parent(3));
    Assert.assertEquals(new Integer(3), gh.parent(4));
    Assert.assertEquals(new Integer(0), gh.parent(5));
    Assert.assertEquals(new Integer(4), gh.parent(6));
    Assert.assertEquals(null, gh.parent(7));
    Assert.assertEquals(null, gh.parent(8));
    Assert.assertEquals(null, gh.parent(9));
    Assert.assertEquals(null, gh.parent(10));
    Assert.assertEquals(null, gh.parent(11));
    Assert.assertEquals(null, gh.parent(12));
    Assert.assertEquals("[3, 5, 0]", gh.pathTo(3).toString());
    Assert.assertEquals("[4, 3, 5, 0]", gh.pathTo(4).toString());
    Assert.assertEquals("[6, 4, 3, 5, 0]", gh.pathTo(6).toString());
  }

  @Test
  public void test_topological() {
    setUpDirected();
    Assert.assertEquals("[1, 2, 3, 4, 5, 0, 8, 12, 10, 11, 9, 6, 7]", gh.topological().toString());
  }
}
