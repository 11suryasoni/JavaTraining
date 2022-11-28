// Shell

mongosh;

// Show Databases

show dbs

// Creating & Selecting DB

use employee

// Creating Collection

db.createCollection("emp")

// Show Collections

show collections

// Drop DB

db.dropDatabase();

// Creating Collection 

db.createCollection("posts")

// Insertion 

// Variable Store Json Object

const doc = {
...   title: "What is the best way to learn JavaScript from the ground up?",
...   postId: NumberInt(3511),
...   comments: 10,
...   shared: true,
...   tags: [
...     "JavaScript",
...     "programming"
...   ],
...   author: {
...     name: "Mike Forester",
...     nickname: "mikef"
...   }
... };


// Variable Store Array of Json Objects

const docArray = [{
...   title: "My thoughts about 12-factor App Methodology",
...   postId: NumberInt(2618),
...   comments: 0,
...   shared: false,
...   tags: [],
...   author: {
...     name: "Emily Watson",
...     nickname: "emily23"
...   }
... },{
...   title: "Who can suggest best computer coding book for beginners?",
...   postId: NumberInt(8451),
...   comments: 2,
...   shared: false,
...   tags: [
...     "programming",
...     "coding"
...   ],
...   author: {
...     name: "Emily Watson",
...     nickname: "emily23"
...   }
... }
... ];

// Inserting Data

db.posts.insertMany(docArray)

// Finding Data 

db.posts.find().pretty()

db.posts.findOne({postId: (3015)})

db.posts.findOne({author: {name : 'Emily Watson'}})

db.posts.find({comments: {$lt : 10}})

db.posts.find({
... $or : [
... {comments : {$gt : 0}},
... {comments : {$lt : 5}}
... ]
... })

db.posts.find({
... $and : [
... {comments: {$gt : 5}},
... {comments: {$lt : 10}}
... ]
... })

db.posts.find({
... $in : [
... {tags : "programming"},
... {tags : "coding"}
... ]
... })

db.posts.find({}).sort({comments: -1})

db.posts.find({}).skip(2).sort({title: 1})

db.posts.find({}).sort({title: 1}).skip(2)


// Updation 

 db.posts.updateOne(
... {postId : 1151},
... {$set : {shared : true}}
... )

db.posts.findOne({postId : 1151})

db.posts.updateOne(
... { postId : 1151},
... { $set : {title : "What is the average salary of the senior frontend developer"}}
... )

db.posts.find({tags :[]})

 db.posts.updateMany(
... {tags : []},
... { $unset : {tags :1}}
... )

db.posts.updateOne(
... {postId : 8451},
... {$inc : {comments: 1}}
... )

db.posts.insertOne({postId: 2222})


// Deletion 

db.posts.deleteOne({postId: 2222})

db.posts.deleteMany( {postId : { $in : [3333,4444]}})

db.posts.insertMany( [{postId : 2222}, {postId : 3333}])

db.posts.find({title : {$exists : false}})

db.posts.deleteMany({title: {$exists : false}})

