# RoomPagingBug

If you save items into database and immediatly try to read them back into PagedList, this PagedList will contain 0 elements (it won't even contain any items that were in the database beforehand).
If you wait 50-100ms after inserting the records, everything works as expected.

See MainActivity#onSaveClicked and MainActivity#onSaveAndWaitClicked.