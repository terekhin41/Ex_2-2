import post.Likes
import post.Post
import java.time.Instant

private var wallId : Int = 0

class WallService {
    init {
        wallId += 1
    }
    private var storage = emptyArray<Post>()
    private var postId : Int = 0
    private var id = wallId

    fun add(post: Post): Post {
        postId += 1
        val publishingPost = post.copy(
            id = postId,
            date = Instant.now().epochSecond.toInt(),
            ownerId = id
        )
        storage += publishingPost
        return storage.last()
    }

    fun update(post: Post): Boolean {
        if (post.ownerId != wallId || post.id > storage.size) return false
        storage[post.id - 1] = post.copy()
        return true
    }

    fun like(post: Post) {
        val likes = Likes(count = post.likes.count + 1)
        update(post.copy(likes = likes))
    }


}