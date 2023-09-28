
# TODO

- [ ] Use MVI and Ribs (or something similar).
- [ ]  @GET("/r/memes/top/?t=all") // TODO add page and limit


# HOW TO MAKE IT WORK
- `LazyColumn` needs the ID.
- Load differently in case of `Prepend` or `Append` and `Refresh`.
- I don't want to leak paging in the domain or data layer.