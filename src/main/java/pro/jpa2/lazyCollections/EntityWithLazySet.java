package pro.jpa2.lazyCollections;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maciej on 2016-12-27.
 */
public class EntityWithLazySet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @JoinColumn(name = "OWNER_ID")
    private Set<Item> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(final Set<Item> items) {
        this.items = items;
    }
}
