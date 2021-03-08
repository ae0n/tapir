package codegen

import codegen.openapi.models.{OpenapiComponent, OpenapiSchemaType}
import codegen.openapi.models.OpenapiModels.{
  OpenapiDocument,
  OpenapiInfo,
  OpenapiParameter,
  OpenapiPath,
  OpenapiPathMethod,
  OpenapiRequestBody,
  OpenapiRequestBodyContent,
  OpenapiResponse,
  OpenapiResponseContent
}
import codegen.openapi.models.OpenapiSchemaType.{
  OpenapiSchemaArray,
  OpenapiSchemaDouble,
  OpenapiSchemaInt,
  OpenapiSchemaObject,
  OpenapiSchemaRef,
  OpenapiSchemaString
}

object TestHelpers {

  val myBookshopYaml =
    """
      |openapi: 3.0.1
      |info:
      |  title: My Bookshop
      |  version: '1.0'
      |paths:
      |  /books/{genre}/{year}:
      |    post:
      |      operationId: postBooksGenreYear
      |      parameters:
      |      - name: genre
      |        in: path
      |        required: true
      |        schema:
      |          type: string
      |      - name: year
      |        in: path
      |        required: true
      |        schema:
      |          type: integer
      |      - name: limit
      |        in: query
      |        description: Maximum number of books to retrieve
      |        required: true
      |        schema:
      |          type: integer
      |      - name: X-Auth-Token
      |        in: header
      |        required: true
      |        schema:
      |          type: string
      |      requestBody:
      |        description: Book to add
      |        required: true
      |        content:
      |          application/json:
      |            schema:
      |              $ref: '#/components/schemas/Book'
      |      responses:
      |        '200':
      |          description: ''
      |          content:
      |            application/json:
      |              schema:
      |                type: array
      |                items:
      |                  $ref: '#/components/schemas/Book'
      |    get:
      |      operationId: getBooksGenreYear
      |      parameters:
      |      - name: genre
      |        in: path
      |        required: true
      |        schema:
      |          type: string
      |      - name: year
      |        in: path
      |        required: true
      |        schema:
      |          type: integer
      |      - name: limit
      |        in: query
      |        description: Maximum number of books to retrieve
      |        required: true
      |        schema:
      |          type: integer
      |      - name: X-Auth-Token
      |        in: header
      |        required: true
      |        schema:
      |          type: string
      |      responses:
      |        '200':
      |          description: ''
      |          content:
      |            application/json:
      |              schema:
      |                type: array
      |                items:
      |                  $ref: '#/components/schemas/Book'
      |        default:
      |          description: ''
      |          content:
      |            text/plain:
      |              schema:
      |                type: string
      |components:
      |  schemas:
      |    Book:
      |      required:
      |      - title
      |      type: object
      |      properties:
      |        title:
      |          type: string
      |""".stripMargin

  val myBookshopDoc = OpenapiDocument(
    "3.0.1",
    OpenapiInfo("My Bookshop", "1.0"),
    Seq(
      OpenapiPath(
        "/books/{genre}/{year}",
        Seq(
          OpenapiPathMethod(
            "post",
            Seq(
              OpenapiParameter("genre", "path", true, None, OpenapiSchemaString(false)),
              OpenapiParameter("year", "path", true, None, OpenapiSchemaInt(false)),
              OpenapiParameter("limit", "query", true, Some("Maximum number of books to retrieve"), OpenapiSchemaInt(false)),
              OpenapiParameter("X-Auth-Token", "header", true, None, OpenapiSchemaString(false))
            ),
            Seq(
              OpenapiResponse(
                "200",
                "",
                Seq(OpenapiResponseContent("application/json", OpenapiSchemaArray(OpenapiSchemaRef("#/components/schemas/Book"), false)))
              )
            ),
            Option(
              OpenapiRequestBody(
                required = true,
                content = Seq(
                  OpenapiRequestBodyContent(
                    "application/json",
                    OpenapiSchemaRef("#/components/schemas/Book")
                  )
                ),
                description = Some("Book to add")
              )
            ),
            None
          ),
          OpenapiPathMethod(
            "get",
            Seq(
              OpenapiParameter("genre", "path", true, None, OpenapiSchemaString(false)),
              OpenapiParameter("year", "path", true, None, OpenapiSchemaInt(false)),
              OpenapiParameter("limit", "query", true, Some("Maximum number of books to retrieve"), OpenapiSchemaInt(false)),
              OpenapiParameter("X-Auth-Token", "header", true, None, OpenapiSchemaString(false))
            ),
            Seq(
              OpenapiResponse(
                "200",
                "",
                Seq(OpenapiResponseContent("application/json", OpenapiSchemaArray(OpenapiSchemaRef("#/components/schemas/Book"), false)))
              ),
              OpenapiResponse("default", "", Seq(OpenapiResponseContent("text/plain", OpenapiSchemaString(false))))
            ),
            None
          )
        )
      )
    ),
    OpenapiComponent(
      Map(
        "Book" -> OpenapiSchemaObject(Map("title" -> OpenapiSchemaString(false)), Seq("title"), false)
      )
    )
  )
}
