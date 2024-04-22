package com.example.todo.repository.task;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    /**
     * Optional<TaskRecord>はJava 8で導入されたjava.util.Optional<T>クラスのインスタンスです。このクラスは、値が存在するかもしれない、
     * または存在しないかもしれない（つまり、nullかもしれない）場合に使用します。
     * 具体的には、Optional<TaskRecord>はTaskRecordオブジェクトを包むラッパーで、TaskRecordオブジェクトが存在するかどうかを明示的に表現します。
     * TaskRecordオブジェクトが存在する場合、そのオブジェクトを取得するためのメソッド（get()やorElse(), orElseThrow()など）が提供されます。
     * TaskRecordオブジェクトが存在しない場合（つまり、nullの場合）、これらのメソッドはデフォルト値を返すか、例外をスローします。
     * このコードのコンテキストでは、select(long taskId)メソッドは、指定されたIDのタスクをデータベースから検索し、
     * そのタスクを表すTaskRecordオブジェクトを返します。しかし、指定されたIDのタスクがデータベースに存在しない場合、
     * このメソッドはnullを返す可能性があります。そのため、このメソッドの戻り値はOptional<TaskRecord>として定義されています。
     * これにより、メソッドの呼び出し元は、戻り値がnullである可能性があることを明示的に認識し、適切に処理することができます。。
     * @param taskId
     * @return
     */
    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(long taskId);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset}")
    List<TaskRecord> selectList(int limit, long offset);

    // insertはvoid出ないといけない、ただし、insertした後のidを取得したい場合は、@Optionsを使う
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    void insert(TaskRecord record);


    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}")
    void update(TaskRecord taskRecord);

    @Delete("DELETE FROM tasks WHERE id = #{taskId}")
    void delete(Long taskId);
}
