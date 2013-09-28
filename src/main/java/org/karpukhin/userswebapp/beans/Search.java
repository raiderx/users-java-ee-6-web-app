package org.karpukhin.userswebapp.beans;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.karpukhin.userswebapp.entities.User;

import javax.inject.Singleton;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Karpukhin
 * @since 24.09.13
 */
@Named
@Singleton
public class Search {

    private IndexWriter indexWriter;
    private SearcherManager searcherManager;

    /**
     * Default constructor
     */
    public Search() {
        try {
            Directory dir = new SimpleFSDirectory(new File("/tmp/users-web-app-index"));
            Analyzer analyzer = new RussianAnalyzer(Version.LUCENE_44);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            indexWriter.commit();
            searcherManager = new SearcherManager(dir, null);
        } catch (IOException e) {
            throw new RuntimeException("Something wrong occurred", e);
        }
    }

    public void addUser(User user) {
        try {
            Document doc = new Document();
            doc.add(new LongField("user_id", user.getId(), Field.Store.YES));
            doc.add(new TextField("user_first_name", user.getFirstName(), Field.Store.NO));
            doc.add(new TextField("user_last_name", user.getLastName(), Field.Store.NO));
            indexWriter.addDocument(doc);
            indexWriter.commit();
            searcherManager.maybeRefresh();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong occurred", e);
        }
    }

    public void updateUser(User user) {
        try {
            Document doc = new Document();
            doc.add(new LongField("user_id", user.getId(), Field.Store.YES));
            doc.add(new TextField("user_first_name", user.getFirstName(), Field.Store.NO));
            doc.add(new TextField("user_last_name", user.getLastName(), Field.Store.NO));
            indexWriter.updateDocument(new Term("user_id", user.getId().toString()), doc);
            indexWriter.commit();
            searcherManager.maybeRefresh();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong occurred", e);
        }
    }

    public List<User> searchUsers(String text) {
        List<User> result = new ArrayList<User>();
        IndexSearcher is = null;
        try {
            is = searcherManager.acquire();
            Query query = new TermQuery(new Term("user_first_name", text));
            TopDocs topDocs = is.search(query, 10);
            for (ScoreDoc sd : topDocs.scoreDocs) {
                Document doc = is.doc(sd.doc);
                Long id = Long.valueOf(doc.get("user_id"));
                User user = new User();
                user.setId(id);
                result.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException("Something wrong occurred", e);
        } finally {
            if (is != null) {
                try {
                    searcherManager.release(is);
                } catch (IOException e) {
                    throw new RuntimeException("Something wrong occurred", e);
                }
            }
        }
        return result;
    }
}
