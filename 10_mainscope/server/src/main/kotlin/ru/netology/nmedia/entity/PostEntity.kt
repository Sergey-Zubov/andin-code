package ru.netology.nmedia.entity

import ru.netology.nmedia.dto.Attachment
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.enumeration.AttachmentType
import javax.persistence.*

@Entity
data class PostEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var author: String,
    var authorAvatar: String,
    @Column(columnDefinition = "TEXT")
    var content: String,
    var published: Long,
    var likedByMe: Boolean,
    var likes: Int = 0,
    @OneToOne(cascade = [CascadeType.ALL])
    var attachment: AttachmentEmbeddable?,
) {
    fun toDto() = Post(id, author, authorAvatar, content, published, likedByMe, likes, attachment?.toDto())

    companion object {
        fun fromDto(dto: Post) = PostEntity(
            dto.id,
            dto.author,
            dto.authorAvatar,
            dto.content,
            dto.published,
            dto.likedByMe,
            dto.likes,
            AttachmentEmbeddable.fromDto(dto.attachment),
        )
    }
}

@Entity
data class AttachmentEmbeddable(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var url: String,
    @Column(columnDefinition = "TEXT")
    var description: String,
    @Enumerated(EnumType.STRING)
    var type: AttachmentType,
) {
    fun toDto() = Attachment(id, url, description, type)

    companion object {
        fun fromDto(dto: Attachment?) = dto?.let {
            AttachmentEmbeddable(it.id, it.url, it.description, it.type)
        }
    }
}
