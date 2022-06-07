/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package src.main.java.AvroByGeneratingClass;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class FootballClub extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4812479295536347319L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FootballClub\",\"namespace\":\"src.main.java.AvroByGeneratingClass\",\"fields\":[{\"name\":\"Name\",\"type\":\"string\"},{\"name\":\"Manager\",\"type\":\"string\"},{\"name\":\"Stadium\",\"type\":\"string\"},{\"name\":\"Trophies\",\"type\":\"int\"},{\"name\":\"Phone\",\"type\":\"long\"},{\"name\":\"PlayerList\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Player\",\"fields\":[{\"name\":\"Name\",\"type\":\"string\"},{\"name\":\"Age\",\"type\":\"int\"},{\"name\":\"Salary\",\"type\":\"long\"}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<FootballClub> ENCODER =
      new BinaryMessageEncoder<FootballClub>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<FootballClub> DECODER =
      new BinaryMessageDecoder<FootballClub>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<FootballClub> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<FootballClub> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<FootballClub> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<FootballClub>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this FootballClub to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a FootballClub from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a FootballClub instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static FootballClub fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence Name;
  private java.lang.CharSequence Manager;
  private java.lang.CharSequence Stadium;
  private int Trophies;
  private long Phone;
  private java.util.List<src.main.java.AvroByGeneratingClass.Player> PlayerList;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FootballClub() {}

  /**
   * All-args constructor.
   * @param Name The new value for Name
   * @param Manager The new value for Manager
   * @param Stadium The new value for Stadium
   * @param Trophies The new value for Trophies
   * @param Phone The new value for Phone
   * @param PlayerList The new value for PlayerList
   */
  public FootballClub(java.lang.CharSequence Name, java.lang.CharSequence Manager, java.lang.CharSequence Stadium, java.lang.Integer Trophies, java.lang.Long Phone, java.util.List<src.main.java.AvroByGeneratingClass.Player> PlayerList) {
    this.Name = Name;
    this.Manager = Manager;
    this.Stadium = Stadium;
    this.Trophies = Trophies;
    this.Phone = Phone;
    this.PlayerList = PlayerList;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return Name;
    case 1: return Manager;
    case 2: return Stadium;
    case 3: return Trophies;
    case 4: return Phone;
    case 5: return PlayerList;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: Name = (java.lang.CharSequence)value$; break;
    case 1: Manager = (java.lang.CharSequence)value$; break;
    case 2: Stadium = (java.lang.CharSequence)value$; break;
    case 3: Trophies = (java.lang.Integer)value$; break;
    case 4: Phone = (java.lang.Long)value$; break;
    case 5: PlayerList = (java.util.List<src.main.java.AvroByGeneratingClass.Player>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'Name' field.
   * @return The value of the 'Name' field.
   */
  public java.lang.CharSequence getName() {
    return Name;
  }


  /**
   * Sets the value of the 'Name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.CharSequence value) {
    this.Name = value;
  }

  /**
   * Gets the value of the 'Manager' field.
   * @return The value of the 'Manager' field.
   */
  public java.lang.CharSequence getManager() {
    return Manager;
  }


  /**
   * Sets the value of the 'Manager' field.
   * @param value the value to set.
   */
  public void setManager(java.lang.CharSequence value) {
    this.Manager = value;
  }

  /**
   * Gets the value of the 'Stadium' field.
   * @return The value of the 'Stadium' field.
   */
  public java.lang.CharSequence getStadium() {
    return Stadium;
  }


  /**
   * Sets the value of the 'Stadium' field.
   * @param value the value to set.
   */
  public void setStadium(java.lang.CharSequence value) {
    this.Stadium = value;
  }

  /**
   * Gets the value of the 'Trophies' field.
   * @return The value of the 'Trophies' field.
   */
  public int getTrophies() {
    return Trophies;
  }


  /**
   * Sets the value of the 'Trophies' field.
   * @param value the value to set.
   */
  public void setTrophies(int value) {
    this.Trophies = value;
  }

  /**
   * Gets the value of the 'Phone' field.
   * @return The value of the 'Phone' field.
   */
  public long getPhone() {
    return Phone;
  }


  /**
   * Sets the value of the 'Phone' field.
   * @param value the value to set.
   */
  public void setPhone(long value) {
    this.Phone = value;
  }

  /**
   * Gets the value of the 'PlayerList' field.
   * @return The value of the 'PlayerList' field.
   */
  public java.util.List<src.main.java.AvroByGeneratingClass.Player> getPlayerList() {
    return PlayerList;
  }


  /**
   * Sets the value of the 'PlayerList' field.
   * @param value the value to set.
   */
  public void setPlayerList(java.util.List<src.main.java.AvroByGeneratingClass.Player> value) {
    this.PlayerList = value;
  }

  /**
   * Creates a new FootballClub RecordBuilder.
   * @return A new FootballClub RecordBuilder
   */
  public static src.main.java.AvroByGeneratingClass.FootballClub.Builder newBuilder() {
    return new src.main.java.AvroByGeneratingClass.FootballClub.Builder();
  }

  /**
   * Creates a new FootballClub RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FootballClub RecordBuilder
   */
  public static src.main.java.AvroByGeneratingClass.FootballClub.Builder newBuilder(src.main.java.AvroByGeneratingClass.FootballClub.Builder other) {
    if (other == null) {
      return new src.main.java.AvroByGeneratingClass.FootballClub.Builder();
    } else {
      return new src.main.java.AvroByGeneratingClass.FootballClub.Builder(other);
    }
  }

  /**
   * Creates a new FootballClub RecordBuilder by copying an existing FootballClub instance.
   * @param other The existing instance to copy.
   * @return A new FootballClub RecordBuilder
   */
  public static src.main.java.AvroByGeneratingClass.FootballClub.Builder newBuilder(src.main.java.AvroByGeneratingClass.FootballClub other) {
    if (other == null) {
      return new src.main.java.AvroByGeneratingClass.FootballClub.Builder();
    } else {
      return new src.main.java.AvroByGeneratingClass.FootballClub.Builder(other);
    }
  }

  /**
   * RecordBuilder for FootballClub instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FootballClub>
    implements org.apache.avro.data.RecordBuilder<FootballClub> {

    private java.lang.CharSequence Name;
    private java.lang.CharSequence Manager;
    private java.lang.CharSequence Stadium;
    private int Trophies;
    private long Phone;
    private java.util.List<src.main.java.AvroByGeneratingClass.Player> PlayerList;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(src.main.java.AvroByGeneratingClass.FootballClub.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.Name)) {
        this.Name = data().deepCopy(fields()[0].schema(), other.Name);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.Manager)) {
        this.Manager = data().deepCopy(fields()[1].schema(), other.Manager);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.Stadium)) {
        this.Stadium = data().deepCopy(fields()[2].schema(), other.Stadium);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.Trophies)) {
        this.Trophies = data().deepCopy(fields()[3].schema(), other.Trophies);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.Phone)) {
        this.Phone = data().deepCopy(fields()[4].schema(), other.Phone);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.PlayerList)) {
        this.PlayerList = data().deepCopy(fields()[5].schema(), other.PlayerList);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing FootballClub instance
     * @param other The existing instance to copy.
     */
    private Builder(src.main.java.AvroByGeneratingClass.FootballClub other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.Name)) {
        this.Name = data().deepCopy(fields()[0].schema(), other.Name);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.Manager)) {
        this.Manager = data().deepCopy(fields()[1].schema(), other.Manager);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.Stadium)) {
        this.Stadium = data().deepCopy(fields()[2].schema(), other.Stadium);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.Trophies)) {
        this.Trophies = data().deepCopy(fields()[3].schema(), other.Trophies);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.Phone)) {
        this.Phone = data().deepCopy(fields()[4].schema(), other.Phone);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.PlayerList)) {
        this.PlayerList = data().deepCopy(fields()[5].schema(), other.PlayerList);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'Name' field.
      * @return The value.
      */
    public java.lang.CharSequence getName() {
      return Name;
    }


    /**
      * Sets the value of the 'Name' field.
      * @param value The value of 'Name'.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder setName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.Name = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'Name' field has been set.
      * @return True if the 'Name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'Name' field.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder clearName() {
      Name = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'Manager' field.
      * @return The value.
      */
    public java.lang.CharSequence getManager() {
      return Manager;
    }


    /**
      * Sets the value of the 'Manager' field.
      * @param value The value of 'Manager'.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder setManager(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.Manager = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'Manager' field has been set.
      * @return True if the 'Manager' field has been set, false otherwise.
      */
    public boolean hasManager() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'Manager' field.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder clearManager() {
      Manager = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'Stadium' field.
      * @return The value.
      */
    public java.lang.CharSequence getStadium() {
      return Stadium;
    }


    /**
      * Sets the value of the 'Stadium' field.
      * @param value The value of 'Stadium'.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder setStadium(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.Stadium = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'Stadium' field has been set.
      * @return True if the 'Stadium' field has been set, false otherwise.
      */
    public boolean hasStadium() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'Stadium' field.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder clearStadium() {
      Stadium = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'Trophies' field.
      * @return The value.
      */
    public int getTrophies() {
      return Trophies;
    }


    /**
      * Sets the value of the 'Trophies' field.
      * @param value The value of 'Trophies'.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder setTrophies(int value) {
      validate(fields()[3], value);
      this.Trophies = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'Trophies' field has been set.
      * @return True if the 'Trophies' field has been set, false otherwise.
      */
    public boolean hasTrophies() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'Trophies' field.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder clearTrophies() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'Phone' field.
      * @return The value.
      */
    public long getPhone() {
      return Phone;
    }


    /**
      * Sets the value of the 'Phone' field.
      * @param value The value of 'Phone'.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder setPhone(long value) {
      validate(fields()[4], value);
      this.Phone = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'Phone' field has been set.
      * @return True if the 'Phone' field has been set, false otherwise.
      */
    public boolean hasPhone() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'Phone' field.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder clearPhone() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'PlayerList' field.
      * @return The value.
      */
    public java.util.List<src.main.java.AvroByGeneratingClass.Player> getPlayerList() {
      return PlayerList;
    }


    /**
      * Sets the value of the 'PlayerList' field.
      * @param value The value of 'PlayerList'.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder setPlayerList(java.util.List<src.main.java.AvroByGeneratingClass.Player> value) {
      validate(fields()[5], value);
      this.PlayerList = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'PlayerList' field has been set.
      * @return True if the 'PlayerList' field has been set, false otherwise.
      */
    public boolean hasPlayerList() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'PlayerList' field.
      * @return This builder.
      */
    public src.main.java.AvroByGeneratingClass.FootballClub.Builder clearPlayerList() {
      PlayerList = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public FootballClub build() {
      try {
        FootballClub record = new FootballClub();
        record.Name = fieldSetFlags()[0] ? this.Name : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.Manager = fieldSetFlags()[1] ? this.Manager : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.Stadium = fieldSetFlags()[2] ? this.Stadium : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.Trophies = fieldSetFlags()[3] ? this.Trophies : (java.lang.Integer) defaultValue(fields()[3]);
        record.Phone = fieldSetFlags()[4] ? this.Phone : (java.lang.Long) defaultValue(fields()[4]);
        record.PlayerList = fieldSetFlags()[5] ? this.PlayerList : (java.util.List<src.main.java.AvroByGeneratingClass.Player>) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<FootballClub>
    WRITER$ = (org.apache.avro.io.DatumWriter<FootballClub>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<FootballClub>
    READER$ = (org.apache.avro.io.DatumReader<FootballClub>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.Name);

    out.writeString(this.Manager);

    out.writeString(this.Stadium);

    out.writeInt(this.Trophies);

    out.writeLong(this.Phone);

    long size0 = this.PlayerList.size();
    out.writeArrayStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (src.main.java.AvroByGeneratingClass.Player e0: this.PlayerList) {
      actualSize0++;
      out.startItem();
      e0.customEncode(out);
    }
    out.writeArrayEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Array-size written was " + size0 + ", but element count was " + actualSize0 + ".");

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.Name = in.readString(this.Name instanceof Utf8 ? (Utf8)this.Name : null);

      this.Manager = in.readString(this.Manager instanceof Utf8 ? (Utf8)this.Manager : null);

      this.Stadium = in.readString(this.Stadium instanceof Utf8 ? (Utf8)this.Stadium : null);

      this.Trophies = in.readInt();

      this.Phone = in.readLong();

      long size0 = in.readArrayStart();
      java.util.List<src.main.java.AvroByGeneratingClass.Player> a0 = this.PlayerList;
      if (a0 == null) {
        a0 = new SpecificData.Array<src.main.java.AvroByGeneratingClass.Player>((int)size0, SCHEMA$.getField("PlayerList").schema());
        this.PlayerList = a0;
      } else a0.clear();
      SpecificData.Array<src.main.java.AvroByGeneratingClass.Player> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<src.main.java.AvroByGeneratingClass.Player>)a0 : null);
      for ( ; 0 < size0; size0 = in.arrayNext()) {
        for ( ; size0 != 0; size0--) {
          src.main.java.AvroByGeneratingClass.Player e0 = (ga0 != null ? ga0.peek() : null);
          if (e0 == null) {
            e0 = new src.main.java.AvroByGeneratingClass.Player();
          }
          e0.customDecode(in);
          a0.add(e0);
        }
      }

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.Name = in.readString(this.Name instanceof Utf8 ? (Utf8)this.Name : null);
          break;

        case 1:
          this.Manager = in.readString(this.Manager instanceof Utf8 ? (Utf8)this.Manager : null);
          break;

        case 2:
          this.Stadium = in.readString(this.Stadium instanceof Utf8 ? (Utf8)this.Stadium : null);
          break;

        case 3:
          this.Trophies = in.readInt();
          break;

        case 4:
          this.Phone = in.readLong();
          break;

        case 5:
          long size0 = in.readArrayStart();
          java.util.List<src.main.java.AvroByGeneratingClass.Player> a0 = this.PlayerList;
          if (a0 == null) {
            a0 = new SpecificData.Array<src.main.java.AvroByGeneratingClass.Player>((int)size0, SCHEMA$.getField("PlayerList").schema());
            this.PlayerList = a0;
          } else a0.clear();
          SpecificData.Array<src.main.java.AvroByGeneratingClass.Player> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<src.main.java.AvroByGeneratingClass.Player>)a0 : null);
          for ( ; 0 < size0; size0 = in.arrayNext()) {
            for ( ; size0 != 0; size0--) {
              src.main.java.AvroByGeneratingClass.Player e0 = (ga0 != null ? ga0.peek() : null);
              if (e0 == null) {
                e0 = new src.main.java.AvroByGeneratingClass.Player();
              }
              e0.customDecode(in);
              a0.add(e0);
            }
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









