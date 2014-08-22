package org.drorzz.elsie.utils;

import java.io.Serializable;

/**
 * PagedHolder is a simple state holder for handling lists of objects,
 * separating them into pages. Page numbering starts with 0.
 *
 * @author Ivansky Denis
 * @since 19.08.2014
 */
@SuppressWarnings("serial")
public class PageHolder implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 30;

    public static final int DEFAULT_MAX_LINKED_PAGES = 7;

    private int count = 0;

    private int pageSize = DEFAULT_PAGE_SIZE;

    private int page = 0;

    private int maxLinkedPages = DEFAULT_MAX_LINKED_PAGES;

    /**
     * Create a new holder instance.
     * You'll need to set a count to be able to use the holder.
     */
    public PageHolder() {}

    /**
     * Create a new holder instance with the given count, starting with
     * a default page size.
     * @param count the count
     */
    public PageHolder(int count) {
        this.count = count;
    }

    /**
     * Create a new holder instance with the given count and page size.
     * @param count the count
     * @param pageSize the page size
     */
    public PageHolder(int count, int pageSize) {
        this.count = count;
        this.pageSize = pageSize;
    }

    /**
     * Return the current page size.
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the count.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Set the current page size.
     * Resets the current page number if changed.
     * <p>Default value is 30.
     */
    public void setPageSize(int pageSize) {
        if (pageSize != this.pageSize) {
            this.pageSize = pageSize;
        }
    }

    /**
     * Return the current page size.
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * Set the current page number.
     * Page numbering starts with 0.
     */
    public void setPage(int page) {
        this.page = Math.min(Math.max(page,0),getPageCount());
    }

    /**
     * Return the current page number.
     * Page numbering starts with 0.
     */
    public int getPage() {
        if (this.page >= getPageCount()) {
            this.page = getPageCount() - 1;
        }
        return this.page;
    }

    /**
     * Set the maximum number of page links to a few pages around the current one.
     */
    public void setMaxLinkedPages(int maxLinkedPages) {
        this.maxLinkedPages = maxLinkedPages;
    }

    /**
     * Return the maximum number of page links to a few pages around the current one.
     */
    public int getMaxLinkedPages() {
        return this.maxLinkedPages;
    }


    /**
     * Return the number of pages for the current source list.
     */
    public int getPageCount() {
        float nrOfPages = (float) getNrOfElements() / getPageSize();
        return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
    }

    /**
     * Return if the current page is the first one.
     */
    public boolean isFirstPage() {
        return getPage() == 0;
    }

    /**
     * Return if the current page is the last one.
     */
    public boolean isLastPage() {
        return getPage() == getPageCount() -1;
    }

    /**
     * Switch to previous page.
     * Will stay on first page if already on first page.
     */
    public void previousPage() {
        if (!isFirstPage()) {
            this.page--;
        }
    }

    /**
     * Switch to next page.
     * Will stay on last page if already on last page.
     */
    public void nextPage() {
        if (!isLastPage()) {
            this.page++;
        }
    }

    /**
     * Return the total number of elements.
     */
    public int getNrOfElements() {
        return getCount();
    }

    /**
     * Return the element index of the first element on the current page.
     * Element numbering starts with 0.
     */
    public int getFirstElementOnPage() {
        return (getPageSize() * getPage());
    }

    /**
     * Return the element index of the last element on the current page.
     * Element numbering starts with 0.
     */
    public int getLastElementOnPage() {
        int endIndex = getPageSize() * (getPage() + 1);
        int size = getNrOfElements();
        return (endIndex > size ? size : endIndex) - 1;
    }

    /**
     * Return the first page to which create a link around the current page.
     */
    public int getFirstLinkedPage() {
        return Math.max(0, getPage() - (getMaxLinkedPages() / 2));
    }

    /**
     * Return the last page to which create a link around the current page.
     */
    public int getLastLinkedPage() {
        return Math.min(getFirstLinkedPage() + getMaxLinkedPages(), getPageCount()) - 1;
    }
}
