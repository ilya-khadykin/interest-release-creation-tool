import com.sperasoft.sie.tools.util.Node;
import com.sperasoft.sie.tools.util.Tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

/**
 * Created by Ilya K on 7/4/2017.
 */
public class TestNgSuiteReader extends SimpleFileVisitor<Path> {

    private Path basePath;

    private Tree<Path> tree;

    // TODO: move hardcoded strings to properties file
    private Collection<String> ignoredDirectories = Arrays.asList("images", "stress-tests", "smoke-tests", "data",
            "bi-tests", "data-cleanup", "data-creation", "helpers", "ingestion-xmls", "manual-team", "temp", "users",
            "unused");

    public TestNgSuiteReader( Path basePath ) {
        this.basePath = basePath;
        this.tree = new Tree<>(basePath);
    }

    public Tree<Path> getTree() {return this.tree; }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if ( ignoredDirectories.contains( dir.getFileName().toString() ) ) {
            return SKIP_SUBTREE;
        } else {
            System.out.format("Directory: %s%n", dir);
            if ( !basePath.equals(dir)) {
                this.getTree().addChildTree(new Tree<>(dir));
            }
            return CONTINUE;
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        // TODO: move hardcoded strings to properties file
        if ( file.toString().toLowerCase().endsWith(".xml") ) {
            // TODO: refactor
            System.out.format("xml file: %s%n", new File(basePath.toString()).toURI().relativize(new File(file.toString()).toURI()).getPath());
            // TODO: read the actual file and if it's not a TestNG suite skip it
            Tree<Path> parentTreeForFile = this.getTree().getTreeByValue(file.getParent());
            parentTreeForFile.addChildNode(new Node<>(file, parentTreeForFile));
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.err.println( String.format("Exception %s occurred while reading %s", exc.getMessage(), file) );
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public static void main(String...args) throws IOException {
        Path pathToSuites = Paths.get("D:\\release_creation_tool\\_for_testing_smoke-tests");

        TestNgSuiteReader testNgSuiteReader = new TestNgSuiteReader(pathToSuites);

        Files.walkFileTree(pathToSuites, testNgSuiteReader );

        System.out.println("===== Test =====");
        testNgSuiteReader.getTree().traverse(
                (Node<Path> node) -> System.out.println("Node: " + node.getData()),
                (Tree<Path> tree) -> System.out.println("Tree: " + tree)
        );

        System.out.println("Success");
    }

    /*
    Read from yaml: https://dzone.com/articles/read-yaml-in-java-with-jackson

     */
}

